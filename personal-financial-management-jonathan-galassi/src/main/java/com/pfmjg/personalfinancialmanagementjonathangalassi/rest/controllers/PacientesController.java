package com.pfmjg.personalfinancialmanagementjonathangalassi.rest.controllers;

import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.Pacientes;
import com.pfmjg.personalfinancialmanagementjonathangalassi.services.PacientesServices;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/pacientes")
@Slf4j
public class PacientesController  {
    @Autowired
    private PacientesServices pacientesServices;

    @GetMapping
    public ResponseEntity<List<Pacientes>> findAll() {
        List<Pacientes> pacientesList = pacientesServices.findAll();
        return ResponseEntity.ok().body(pacientesList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Pacientes> finById(@PathVariable Integer id) {
        Pacientes obj = pacientesServices.findbyId(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping(value = "/cadastrar-paciente")
    public ResponseEntity<Pacientes> insertPaciente(@RequestBody Pacientes pac) {
        pac = pacientesServices.insertPaciente(pac);
        return ResponseEntity.ok().body(pac);
    }

    @GetMapping(value = "/nome-{nomePaciente}")
    public ResponseEntity<List<String>> searchByName(@PathVariable String nomePaciente) {
        if (nomePaciente == null || nomePaciente.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        List<String> pac = pacientesServices.searchByName(nomePaciente);
        return new ResponseEntity<List<String>>(pac, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/deletar-paciente-{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        pacientesServices.deleteById(id);
        return ResponseEntity.noContent().build();
    }



}
