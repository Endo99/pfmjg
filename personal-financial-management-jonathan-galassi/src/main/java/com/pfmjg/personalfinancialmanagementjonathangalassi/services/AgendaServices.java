package com.pfmjg.personalfinancialmanagementjonathangalassi.services;

import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.dto.AgendaDTO;
import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.dto.PacienteDTO;
import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.agenda.Agenda;
import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.paciente.Paciente;
import com.pfmjg.personalfinancialmanagementjonathangalassi.repository.AgendaRepository;
import com.pfmjg.personalfinancialmanagementjonathangalassi.repository.PacienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgendaServices {

    @Autowired
    private AgendaRepository agendaRepository;
    @Autowired
    private PacienteRepository pacienteRepository;


    public AgendaServices() {

    }

//    public boolean verificarId(Integer id) {
//        return agendaRepository.existsById(id);
//    }

    public List<Agenda> findAll() {
        return agendaRepository.findAll();
    }

    public Agenda findbyId(Integer id) {
        Optional<Agenda> obj = agendaRepository.findById(id);
        return obj.get();
    }


    public Agenda criarAgenda(Integer idPaciente, Agenda agenda) {
        System.out.println("Valor recebido para o campo idAgendamento: " + agenda.getIdAgenda());
        System.out.println("Valor recebido para o campo idPaciente: " + agenda.getPaciente().getIdPaciente());

        // Verifique se o paciente existe no banco de dados
        Paciente paciente = pacienteRepository.findById(idPaciente)
                .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado com o ID: " + idPaciente));

        // Associe o paciente à agenda
        agenda.setPaciente(paciente);

        // Salve a agenda no banco de dados
        return agendaRepository.save(agenda);
    }

    public void deleteById(Integer id) {
        agendaRepository.deleteById(id);
    }


    public Agenda updateAgendamentoa(Integer id, Agenda agenda) {

        System.out.println("Valor recebido para o campo idAgendamento: " + id);
        System.out.println("Valor recebido para o campo idAgendamento: " + agenda.getIdAgenda());

        Agenda entity = agendaRepository.getReferenceById(id);
        updateData(entity, agenda);
        return agendaRepository.save(entity);
    }

    private void updateData(Agenda entity, Agenda agenda) {
        entity.setDataInicio(agenda.getDataInicio());
        entity.setDescricao(agenda.getDescricao());
        entity.setHorarioInicio(agenda.getHorarioInicio());
        entity.setHoraFinal(agenda.getHoraFinal());
        entity.setObservacao(agenda.getObservacao());

    }

    public Agenda getAgendamentoById(Integer idAgenda) {
        return agendaRepository.findById(idAgenda)
                .orElseThrow(() -> new EntityNotFoundException("Agendamento não encontrado com o ID: " + idAgenda));
    }

    public Agenda getConsultaById(Integer idConsulta) {
        return agendaRepository.findById(idConsulta)
                .orElseThrow(() -> new EntityNotFoundException("Consulta não encontrado com o ID: " + idConsulta));
    }

    public Agenda getPaciente(Integer idAgenda) {
        return agendaRepository.findAgendamentoWithPacienteDetails(idAgenda);
    }

    public List<Agenda> findAgendasByPacienteId(Integer idPaciente) {
        return agendaRepository.findByPacienteId(idPaciente);
    }

    public AgendaDTO getDetalhesDaAgendaPorDescricao(String descricao) {
        // Implemente a lógica para buscar detalhes do paciente com base no CPF
        Agenda agenda = agendaRepository.findByDescription(descricao);

        if (agenda != null) {
            // Mapeie os detalhes do paciente para um DTO
            AgendaDTO agendaDTO = new AgendaDTO();
            agendaDTO.setDescricao(agenda.getDescricao());
            agendaDTO.setObservacao(agenda.getObservacao());
            agendaDTO.setDataInicio(agenda.getDataInicio());
            agendaDTO.setHorarioInicio(agenda.getHorarioInicio());
            agendaDTO.setHoraFinal(agenda.getHoraFinal());
            agendaDTO.setPaciente(agenda.getPaciente());

            System.out.println(agendaDTO.getDescricao());
            System.out.println(agendaDTO.getObservacao());
            System.out.println(agendaDTO.getHoraFinal());
            System.out.println(agendaDTO.getHorarioInicio());
            System.out.println(agendaDTO.getDataInicio());
            System.out.println(agenda.getPaciente());

            return agendaDTO;
        } else {
            return null; // Ou lance uma exceção se o paciente não for encontrado
        }
    }

}
