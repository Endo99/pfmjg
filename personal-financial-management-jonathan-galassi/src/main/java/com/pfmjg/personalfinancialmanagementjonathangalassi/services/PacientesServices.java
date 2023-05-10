package com.pfmjg.personalfinancialmanagementjonathangalassi.services;

import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.Pacientes;
import com.pfmjg.personalfinancialmanagementjonathangalassi.repository.PacientesRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Service
public class PacientesServices {

    @Autowired
    private PacientesRepository pacientesRepository;

    public PacientesServices() {

    }


    public List<Pacientes> findAll() {
        return pacientesRepository.findAll();
    }

    public Pacientes findbyId(Integer id) {
        Optional<Pacientes> obj = pacientesRepository.findById(id);
        return obj.get();
    }

    public List<String> searchByName(String nomePaciente) {

        return  pacientesRepository.searchByName(nomePaciente);
    }

    public Pacientes insertPaciente(Pacientes pacientes) {
        return pacientesRepository.save(pacientes);
    }

    public void deleteById(Integer id) {
        pacientesRepository.deleteById(id);
    }

    public Pacientes update(Integer id, Pacientes pacientes) {
        Pacientes entity = pacientesRepository.getReferenceById(id);
        updateData(entity, pacientes);
        return pacientesRepository.save(entity);
    }

    private void updateData(Pacientes entity, Pacientes pacientes) {
        entity.setNomePaciente(pacientes.getNomePaciente());
        entity.setSobrenomePaciente(pacientes.getSobrenomePaciente());
        entity.setDataNascimentoPaciente(pacientes.getDataNascimentoPaciente());
        entity.setMesesAcompanhado(pacientes.getMesesAcompanhado());
        entity.setQuantiaPaga(pacientes.getQuantiaPaga());
        entity.setFormaPagamento(pacientes.getFormaPagamento());
        entity.setTipoConsulta(pacientes.getTipoConsulta());
        entity.setCidade(pacientes.getCidade());
        entity.setTelefone(pacientes.getTelefone());
        entity.setEstado(pacientes.getEstado());
        entity.setStatusPagamento(pacientes.getStatusPagamento());
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

