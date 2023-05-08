package com.pfmjg.personalfinancialmanagementjonathangalassi.resources;

import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.Pacientes;
import com.pfmjg.personalfinancialmanagementjonathangalassi.services.PacientesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/pacientes")
public class PacientesResources {

    @Autowired
    private PacientesServices pacientesServices;

    @PostMapping
    public ResponseEntity<List<Pacientes>> findAll() {
        List<Pacientes> pacientesList = pacientesServices.findAll();
        return ResponseEntity.ok().body(pacientesList);
    }
}
