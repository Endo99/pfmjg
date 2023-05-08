package com.pfmjg.personalfinancialmanagementjonathangalassi.services;

import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.Pacientes;
import com.pfmjg.personalfinancialmanagementjonathangalassi.repository.PacientesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class PacientesServices {

    @Autowired
    private PacientesRepository pacientesRepository;

    public PacientesServices() {

    }

    public List<Pacientes> findAll() {
        return pacientesRepository.findAll();
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

