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

    public Pacientes insert(Pacientes pacientes) {
        return pacientesRepository.save(pacientes);
    }

//    public Pacientes deleteById(Integer id) {
//        return pacientesRepository.delete(id);
//    }

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

