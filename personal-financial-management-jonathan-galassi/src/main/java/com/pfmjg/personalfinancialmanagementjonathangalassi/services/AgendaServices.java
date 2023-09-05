package com.pfmjg.personalfinancialmanagementjonathangalassi.services;

import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.Agenda;
import com.pfmjg.personalfinancialmanagementjonathangalassi.repository.AgendaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgendaServices {

    @Autowired
    private AgendaRepository agendaRepository;

    public AgendaServices() {

    }

    public boolean verificarId(Integer id) {
        return agendaRepository.existsById(id);
    }

    public List<Agenda> findAll() {
        return agendaRepository.findAll();
    }

    public Agenda findbyId(Integer id) {
        Optional<Agenda> obj = agendaRepository.findById(id);
        return obj.get();
    }

    public Agenda insertAgendamento(Agenda agenda) {

        System.out.println("Valor recebido para o campo idAgendamento: " + agenda.getIdAgenda());
        System.out.println("Valor recebido para o campo Paciente: " + agenda.getPaciente());
        System.out.println("Valor recebido para o campo Consulta: " + agenda.getConsulta());

        if (verificarId(agenda.getIdAgenda())) {
            throw new IllegalArgumentException("O ID já existe");
        }
//        Paciente existPaciente = categoriaRepository.findByNomePacienteAndAndSobrenomePacienteAndDataNascimentoPaciente(paciente.getNomePaciente(),
//                categoria.getSobrenomePaciente(), categoria.getDataNascimentoPaciente());
//        if (existPaciente != null) {
//            throw new DataIntegrityViolationException("Já existe esse usuário");
//        }
        return agendaRepository.save(agenda);
    }

    public void deleteById(Integer id) {
        agendaRepository.deleteById(id);
    }

    public Agenda updateAgendamentoa(Integer id, Agenda agenda) {

        System.out.println("Valor recebido para o campo idAgendamento: " + agenda.getIdAgenda());
        System.out.println("Valor recebido para o campo Paciente: " + agenda.getPaciente());
        System.out.println("Valor recebido para o campo Consulta: " + agenda.getConsulta());

        Agenda entity = agendaRepository.getReferenceById(id);
        updateData(entity, agenda);
        return agendaRepository.save(entity);
    }

    private void updateData(Agenda entity, Agenda agenda) {
        entity.setPaciente(agenda.getPaciente());
        entity.setConsulta(agenda.getConsulta());
    }

    public Agenda getAgendamentoById(Integer idAgenda) {
        return agendaRepository.findById(idAgenda)
                .orElseThrow(() -> new EntityNotFoundException("Agendamento não encontrado com o ID: " + idAgenda));
    }
}
