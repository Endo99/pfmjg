package com.pfmjg.personalfinancialmanagementjonathangalassi.services;

import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.agenda.Agenda;
import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.consulta.Consulta;
import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.dto.ConsultaDTO;
import com.pfmjg.personalfinancialmanagementjonathangalassi.repository.AgendaRepository;
import com.pfmjg.personalfinancialmanagementjonathangalassi.repository.ConsultaRepository;
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

    public ConsultaServices() {

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

    public ConsultaDTO consultarPorId(Integer id) {
        System.out.println("Id consultaDTO:" + id);
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Consulta não encontrada com o ID: " + id));

        // Mapeie a entidade para o DTO
        ConsultaDTO consultaDTO = modelMapper.map(consulta, ConsultaDTO.class);

        return consultaDTO;
    }

    public List<Consulta> findConsultasByTipo(String tipo) {
        return consultaRepository.findByTipoConsulta(tipo);
    }

    public Consulta insertConsulta(Integer idAgenda, Consulta consulta) {

        System.out.println("Valor recebido para o campo idConsulta: " + consulta.getIdConsulta());


        // Primeiro, verifique se a agenda com o ID fornecido existe
        Optional<Agenda> agendaOptional = agendaRepository.findById(idAgenda);

        if (agendaOptional.isPresent()) {
            Agenda agenda = agendaOptional.get();

            consulta.setAgenda(agenda);

            return consultaRepository.save(consulta);
        } else {

            throw new EntityNotFoundException("Agenda com o ID " + idAgenda + " não encontrada");
        }
    }


    public Consulta cadastrarConsulta(Consulta consulta) {

        System.out.println("Valor recebido para o campo idConsulta: " + consulta.getIdConsulta());

        return consultaRepository.save(consulta);


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
        entity.setAgenda(consulta.getAgenda());
        entity.setTipoConsulta(consulta.getTipoConsulta());
        entity.setFormaPagamento(consulta.getFormaPagamento());
        entity.setMesesAcompanhado(consulta.getMesesAcompanhado());
    }
}
