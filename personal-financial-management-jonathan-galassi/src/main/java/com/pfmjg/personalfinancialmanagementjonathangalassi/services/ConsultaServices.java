package com.pfmjg.personalfinancialmanagementjonathangalassi.services;

import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.dto.AgendaDTO;
import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.dto.PacienteDTO;
import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.agenda.Agenda;
import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.consulta.Consulta;
import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.dto.ConsultaDTO;
import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.paciente.Paciente;
import com.pfmjg.personalfinancialmanagementjonathangalassi.repository.AgendaRepository;
import com.pfmjg.personalfinancialmanagementjonathangalassi.repository.ConsultaRepository;
import com.pfmjg.personalfinancialmanagementjonathangalassi.repository.PacienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultaServices {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private ModelMapper modelMapper;
    private final PacienteRepository pacienteRepository;

    public ConsultaServices(PacienteRepository pacienteRepository) {

        this.pacienteRepository = pacienteRepository;
    }

    public List<Consulta> findAll() {
        return consultaRepository.findAll();
    }


    public List<Consulta> findConsultasByTipo(String tipo) {
        return consultaRepository.findByTipoConsulta(tipo);
    }

    public Consulta insertConsulta(Consulta consulta) {
        try {

            // Salve a consulta no banco de dado
            return consultaRepository.save(consulta);
        } catch (EntityNotFoundException e) {
            throw e; // Você pode lançar a exceção novamente ou tratar de acordo com suas necessidades.
        } catch (Exception e) {
            throw e; // Trate outras exceções de acordo com suas necessidades.
        }
    }

    public void deleteById(Integer id) {
        consultaRepository.deleteById(id);
    }

    public Consulta updateConsulta(Integer id, Consulta consulta) {

        System.out.println("Valor recebido para o campo idConsulta: " + consulta.getIdConsulta());

        Consulta entity = consultaRepository.getReferenceById(id);
        updateData(entity, consulta);
        return consultaRepository.save(entity);
    }

    private void updateData(Consulta entity, Consulta consulta) {
        entity.setDataConsultaAtual(consulta.getDataConsultaAtual());
        entity.setTipoConsulta(consulta.getTipoConsulta());
        entity.setFormaPagamento(consulta.getFormaPagamento());
    }

    public List<Consulta> findConsultasByPacienteId(Integer idPaciente) {
        return consultaRepository.findByPacienteId(idPaciente);
    }


}
