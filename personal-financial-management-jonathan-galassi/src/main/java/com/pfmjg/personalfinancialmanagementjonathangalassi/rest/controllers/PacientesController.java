package com.pfmjg.personalfinancialmanagementjonathangalassi.rest.controllers;

import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.Pacientes;
import com.pfmjg.personalfinancialmanagementjonathangalassi.repository.PacientesRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
public class PacientesController  {
    private final PacientesRepository pacientesRepository;

    public PacientesController(PacientesRepository pacientesRepository) {
        this.pacientesRepository = pacientesRepository;
    }

    @PostMapping(value = "/cadastrar-paciente")
    @ResponseBody
    public Pacientes cadastrarPacientes(Pacientes pacientes) {
        return pacientesRepository.save(pacientes);
    }
}
