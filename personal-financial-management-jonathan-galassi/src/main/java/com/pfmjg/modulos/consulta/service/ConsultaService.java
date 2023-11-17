package com.pfmjg.modulos.consulta.service;

import com.pfmjg.modulos.agenda.dto.AgendaResponse;
import com.pfmjg.modulos.agenda.model.Agenda;
import com.pfmjg.modulos.agenda.predicate.AgendaPredicate;
import com.pfmjg.modulos.agenda.service.AgendaService;
import com.pfmjg.modulos.comum.enums.ESituacao;
import com.pfmjg.modulos.comum.exception.NotFoundException;
import com.pfmjg.modulos.comum.exception.ValidacaoException;
import com.pfmjg.modulos.consulta.dto.ConsultaFiltros;
import com.pfmjg.modulos.consulta.dto.ConsultaRequest;
import com.pfmjg.modulos.consulta.dto.ConsultaResponse;
import com.pfmjg.modulos.consulta.enums.ESituacaoConsulta;
import com.pfmjg.modulos.consulta.model.Consulta;
import com.pfmjg.modulos.consulta.predicate.ConsultaPredicate;
import com.pfmjg.modulos.consulta.repository.ConsultaRepository;
import com.pfmjg.modulos.paciente.service.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.pfmjg.modulos.comum.util.DateUtil.diasAgenda;
import static com.pfmjg.modulos.comum.util.DateUtil.validarDataEPeriodoAtual;
import static com.pfmjg.modulos.comum.util.TimeUtil.horariosAgenda;

@Service
@RequiredArgsConstructor
public class ConsultaService {

    private final AgendaService agendaService;
    private final ConsultaRepository repository;
    private final PacienteService pacienteService;

    public Consulta buscarPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Consulta não encontrada"));
    }

    public List<ConsultaResponse> buscarTodos(ConsultaFiltros filtros) {
        var consultas = (List<Consulta>) repository.findAll();
        return consultas.stream()
                .map(ConsultaResponse::of)
                .collect(Collectors.toList());
    }

    public ConsultaResponse salvar(ConsultaRequest request) {
        validarDataEPeriodoAtual(request.data());
        var paciente = pacienteService.buscarPorId(request.pacienteId());
        var agenda = agendaService.buscarPorId(request.agendaId());
        var consulta = Consulta.of(request, paciente, agenda);
        validarAgendaLivre(request.agendaId(), request.data(), request.horaInicial());
        return ConsultaResponse.of(repository.save(consulta));
    }

    public ConsultaResponse alterar(Integer id, ConsultaRequest request) {
        validarDataEPeriodoAtual(request.data());
        var paciente = pacienteService.buscarPorId(request.pacienteId());
        var agenda = agendaService.buscarPorId(request.agendaId());
        var consultaAntiga = buscarPorId(id);
        var ConsultaNova = Consulta.of(request, paciente, new Agenda());
        validarAgendaLivre(request.agendaId(), request.data(), request.horaInicial());

        BeanUtils.copyProperties(ConsultaNova, consultaAntiga, "id", "situacao");

        return ConsultaResponse.of(repository.save(consultaAntiga));
    }

    public void checkIn(Integer id) {
        var consulta = buscarPorId(id);
        consulta.setSituacao(ESituacaoConsulta.EM_ANDAMENTO);

        repository.save(consulta);
    }

    public void finalizar(Integer id) {
        var consulta = buscarPorId(id);
        consulta.setSituacao(ESituacaoConsulta.FINALIZADA);

        repository.save(consulta);
    }

    public void cancelar(Integer id) {
        var consulta = buscarPorId(id);
        consulta.setSituacao(ESituacaoConsulta.CANCELADA);

        repository.save(consulta);
    }

    public void atribuirFalta() {
        var consultaPredicate = new ConsultaPredicate();
        var predicate = consultaPredicate.comSituacoes(List.of(ESituacaoConsulta.AGENDADO))
                .comDataAnterior(LocalDate.now()).build();
        var consultas = repository.findAll(predicate);

        consultas.forEach(consulta -> {
            consulta.setSituacao(ESituacaoConsulta.FALTA);
            repository.save(consulta);
        });
    }

    public void finalizarTodasConsultasPassadas() {
        var consultaPredicate = new ConsultaPredicate();
        var predicate = consultaPredicate.comSituacoes(List.of(ESituacaoConsulta.EM_ANDAMENTO))
                .comDataAnterior(LocalDate.now()).build();
        var consultas = repository.findAll(predicate);

        consultas.forEach(consulta -> {
            consulta.setSituacao(ESituacaoConsulta.FINALIZADA);
            repository.save(consulta);
        });
    }

    public List<LocalDate> diasComAgenda() {
        var agendaPredicate = new AgendaPredicate();
        var predicate = agendaPredicate.comSituacao(ESituacao.ATIVO).comData().build();
        var result = agendaService.buscarDiasComAgenda(predicate);
        return diasAgenda(result.get(0, LocalDate.class),
                Objects.requireNonNull(result.get(1, LocalDate.class)));
    }

    public List<AgendaResponse> agendaPorData(LocalDate data) {
        var agendas = agendaService.buscarAgendasPorData(data);
        return agendas.stream()
                .map(AgendaResponse::of)
                .collect(Collectors.toList());
    }

    public List<LocalTime> horariosLivres(Integer agendaId, LocalDate data) {
        var agenda = agendaService.buscarPorId(agendaId);
        var horariosOcupados = repository.buscarHorariosPorAgendaId(agendaId, data);

        var horarios = horariosAgenda(agenda.getHoraDiaInicial(),
                agenda.getHoraDiaFinal(), agenda.getTempoPadrao());

        horarios.removeAll(horariosOcupados);

        return horarios;
    }

    private void validarAgendaLivre(Integer agendaId, LocalDate data, LocalTime hora) {
        List<LocalTime> horariosLivres = horariosLivres(agendaId, data);

        if (horariosLivres == null || horariosLivres.stream().noneMatch(h -> h.equals(hora))) {
            throw new ValidacaoException("Agenda não está disponível nesse horário");
        }
    }
}
