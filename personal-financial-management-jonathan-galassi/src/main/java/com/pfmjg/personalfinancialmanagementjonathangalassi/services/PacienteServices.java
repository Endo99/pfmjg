package com.pfmjg.personalfinancialmanagementjonathangalassi.services;

import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.Paciente;
import com.pfmjg.personalfinancialmanagementjonathangalassi.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteServices {

    @Autowired
    private PacienteRepository pacienteRepository;

    public PacienteServices() {

    }


    public List<Paciente> findAll() {
        return pacienteRepository.findAll();
    }

    public Paciente findbyId(Integer id) {
        Optional<Paciente> obj = pacienteRepository.findById(id);
        return obj.get();
    }

    public List<String> searchByName(String nomePaciente) {

        return  pacienteRepository.searchByName(nomePaciente);
    }

    public Paciente insertPaciente(Paciente paciente) {
        Paciente existPaciente = pacienteRepository.findByNomePacienteAndAndSobrenomePacienteAndDataNascimentoPaciente(paciente.getNomePaciente(),
                paciente.getSobrenomePaciente(), paciente.getDataNascimentoPaciente());
        if (existPaciente != null) {
            throw new DataIntegrityViolationException("Já existe esse usuário");
        }
        return pacienteRepository.save(paciente);
    }

    public void deleteById(Integer id) {
        pacienteRepository.deleteById(id);
    }

    public Paciente updatePaciente(Integer id, Paciente paciente) {
        Paciente entity = pacienteRepository.getReferenceById(id);
        updateData(entity, paciente);
        return pacienteRepository.save(entity);
    }

    private void updateData(Paciente entity, Paciente paciente) {
        entity.setNomePaciente(paciente.getNomePaciente());
        entity.setSobrenomePaciente(paciente.getSobrenomePaciente());
        entity.setDataNascimentoPaciente(paciente.getDataNascimentoPaciente());
        entity.setMesesAcompanhado(paciente.getMesesAcompanhado());
        entity.setQuantiaPaga(paciente.getQuantiaPaga());
        entity.setFormaPagamento(paciente.getFormaPagamento());
        entity.setTipoConsulta(paciente.getTipoConsulta());
        entity.setCidade(paciente.getCidade());
        entity.setTelefone(paciente.getTelefone());
        entity.setEstado(paciente.getEstado());
        entity.setStatusPagamento(paciente.getStatusPagamento());
        entity.setValorConsulta(paciente.getValorConsulta());
        entity.setIdadePaciente(paciente.getIdadePaciente());
    }

//    @PostMapping("/cadastrar")
//    @Transactional
//    @ResponseBody
//    public List<Pacientes> cadastrarPaciente(@RequestBody Pacientes pacientes) {
//        return (List<Pacientes>) new pacientesRepository.save(pacientes);
//    }
//
//    @PostMapping("/editar/{id}")
//    @Transactional
//    @ResponseBody
//    public String editarDadoPaciente( @RequestBody Pacientes pacientes) {
//        return "";
//    }
//
//    @DeleteMapping("/delete/{id}")
//    @Transactional
//    @ResponseBody
//    public String deletarPaciente(Pacientes pacientes) {
//
//        return "";
//    }

}

