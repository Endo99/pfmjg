package com.pfmjg.personalfinancialmanagementjonathangalassi.services;

import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.dto.BuscaCPFDTO;
import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.dto.DadosRelacionadosDTO;
import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.agenda.Agenda;
import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.consulta.Consulta;
import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.paciente.Paciente;
import com.pfmjg.personalfinancialmanagementjonathangalassi.repository.AgendaRepository;
import com.pfmjg.personalfinancialmanagementjonathangalassi.repository.ConsultaRepository;
import com.pfmjg.personalfinancialmanagementjonathangalassi.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DadosRelacionadosService {

    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private AgendaRepository agendaRepository;

    public List<DadosRelacionadosDTO> buscarDadosRelacionados() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        List<DadosRelacionadosDTO> dadosRelacionados = new ArrayList<>();

        for (Paciente paciente : pacientes) {

            List<Consulta> consultas = consultaRepository.findByPacienteId(paciente.getIdPaciente());
            List<Agenda> agendamentos = agendaRepository.findByPacienteId(paciente.getIdPaciente());

            Consulta consulta = !consultas.isEmpty() ? consultas.get(0) : null;
            Agenda agendamento = !agendamentos.isEmpty() ? agendamentos.get(0) : null;

            DadosRelacionadosDTO dados = new DadosRelacionadosDTO(
                    paciente.getNomePaciente(),
                    consulta != null ? consulta.getTipoConsulta() : "Sem dados",
                    consulta != null ? String.valueOf(consulta.getDataConsultaAtual()) : "Sem dados",
                    agendamento != null ? String.valueOf(agendamento.getHorarioInicio()) : "Sem dados"
            );

            dadosRelacionados.add(dados);
        }

        return dadosRelacionados;
    }

    public List<BuscaCPFDTO> findAllCpfNomes() {
        return pacienteRepository.findAllCpfNomes();
    }

}
