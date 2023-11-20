package com.pfmjg.modulos.agenda.service;

import com.pfmjg.modulos.agenda.dto.AgendaFiltros;
import com.pfmjg.modulos.agenda.dto.AgendaRequest;
import com.pfmjg.modulos.agenda.dto.AgendaResponse;
import com.pfmjg.modulos.agenda.model.Agenda;
import com.pfmjg.modulos.agenda.repository.AgendaRepository;
import com.pfmjg.modulos.comum.enums.ESituacao;
import com.pfmjg.modulos.comum.exception.NotFoundException;
import com.pfmjg.modulos.consulta.dto.ConsultaFiltros;
import com.pfmjg.modulos.consulta.service.ConsultaService;
import com.pfmjg.modulos.nutricionista.service.NutricionistaService;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.pfmjg.modulos.comum.util.DateUtil.validarDataEPeriodoAtual;
import static com.pfmjg.modulos.comum.util.DateUtil.validarPeriodoInicialMenorFinal;
import static com.pfmjg.modulos.comum.util.TimeUtil.validarHoraPeriodoInicialMenorFinal;
import static com.pfmjg.modulos.comum.util.TimeUtil.validarHorarioEPeriodoAtual;

@Service
@RequiredArgsConstructor
public class AgendaService {

    private final AgendaRepository repository;
    private final NutricionistaService nutricionistaService;

    @Lazy
    @Autowired
    private ConsultaService consultaService;

    public Agenda buscarPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Agenda não encontrada"));
    }

    public List<AgendaResponse> buscarTodos(AgendaFiltros filtros) {
        var agendas = (List<Agenda>) repository.findAll(filtros.toPredicate().build());
        return agendas.stream()
                .map(AgendaResponse::of)
                .collect(Collectors.toList());
    }

    public AgendaResponse salvar(AgendaRequest request) {
        validarDataEHora(request.dataInicial(), request.dataFinal(), request.horaDiaInicial(), request.horaDiaFinal());
        var nutricionista = nutricionistaService.buscarPorId(request.nutricionistaId());
        var agenda = Agenda.of(request, nutricionista);
        return AgendaResponse.of(repository.save(agenda));
    }

    public AgendaResponse alterar(Integer id, AgendaRequest request) {
        validarDataEHora(request.dataInicial(), request.dataFinal(), request.horaDiaInicial(), request.horaDiaFinal());
        var nutricionista = nutricionistaService.buscarPorId(request.nutricionistaId());

        var agendaAntiga = buscarPorId(id);
        var agendaNova = Agenda.of(request, nutricionista);

        BeanUtils.copyProperties(agendaNova, agendaAntiga, "id", "situacao");

        return AgendaResponse.of(repository.save(agendaAntiga));
    }

    public void ativar(Integer id) {
        var agenda = buscarPorId(id);
        agenda.setSituacao(ESituacao.ATIVO);

        repository.save(agenda);
    }

    public void inativar(Integer id) {
        var agenda = buscarPorId(id);
        agenda.setSituacao(ESituacao.INATIVO);
        cancelarConsultas(agenda.getId());

        repository.save(agenda);
    }

    public void inativarPorNutricionista(Integer nutricionistaId) {
        var filtros = new AgendaFiltros(null, null, null, nutricionistaId);
        var agendas = findAll(filtros);

        if (!agendas.isEmpty()) {
            agendas.forEach(agenda -> {
                agenda.setSituacao(ESituacao.INATIVO);
                cancelarConsultas(agenda.getId());
            });
            repository.saveAll(agendas);
        }
    }

    public Tuple buscarDiasComAgenda(Predicate predicate) {
        return repository.buscarDiasComAgenda(predicate);
    }

    public List<Agenda> buscarAgendasPorData(LocalDate data) {
        return repository.buscarAgendasPorData(data);
    }

    private void validarDataEHora(LocalDate dataInicial, LocalDate dataFinal,
                                  LocalTime horaInicial, LocalTime horaFinal) {
        validarDataEPeriodoAtual(dataInicial);
        validarPeriodoInicialMenorFinal(dataInicial, dataFinal);
        validarHorarioEPeriodoAtual(horaInicial, dataInicial);
        validarHoraPeriodoInicialMenorFinal(horaInicial, horaFinal);
    }

    private List<Agenda> findAll(AgendaFiltros filtros) {
        return (List<Agenda>) repository.findAll(filtros.toPredicate().build());
    }

    private void cancelarConsultas(Integer agendaId) {
        var filtro = new ConsultaFiltros(null, null, null, List.of(agendaId));

        consultaService.cancelarVarios(filtro);
    }
}
