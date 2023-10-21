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

    public boolean verificarId(Integer id) {
        return consultaRepository.existsById(id);
    }

    public List<Consulta> findAll() {
        return consultaRepository.findAll();
    }

    public Consulta findbyId(Integer id) {
        Optional<Consulta> obj = consultaRepository.findById(id);
        return obj.get();
    }

//    public ConsultaDTO consultarPorId(Integer id) {
//        System.out.println("Id consultaDTO:" + id);
//        Consulta consulta = consultaRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Consulta não encontrada com o ID: " + id));
//
//        // Mapeie a entidade para o DTO
//        ConsultaDTO consultaDTO = modelMapper.map(consulta, ConsultaDTO.class);
//
//        // Mapeie informações da agenda
//        Agenda agenda = consulta.getAgenda();
//        AgendaDTO agendaDTO = modelMapper.map(agenda, AgendaDTO.class);
//        consultaDTO.setAgenda(agendaDTO);
//
//        // Mapeie informações do paciente
//        Paciente paciente = agenda.getIdPaciente();
//        PacienteDTO pacienteDTO = modelMapper.map(paciente, PacienteDTO.class);
//        consultaDTO.setPaciente(pacienteDTO);
//
//        return consultaDTO;
//    }

    public List<Consulta> findConsultasByTipo(String tipo) {
        return consultaRepository.findByTipoConsulta(tipo);
    }

    public Consulta insertConsulta(Consulta consulta) {

        System.out.println("Valor recebido para o campo idConsulta: " + consulta.getIdConsulta());


        return consultaRepository.save(consulta);
    }


    public Consulta cadastrarConsulta(Consulta consulta) {

        System.out.println("Valor recebido para o campo idConsulta: " + consulta.getIdConsulta());

        return consultaRepository.save(consulta);


    }

    public Consulta insertConsulta(Integer idPaciente, Consulta consulta) {
        try {
            // Busque o paciente correspondente ao ID
            Paciente paciente = pacienteRepository.findById(idPaciente)
                    .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado com o ID: " + idPaciente));

            // Associe o paciente à consulta
            consulta.setPaciente(paciente);

            // Salve a consulta no banco de dados
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
