package com.pfmjg.personalfinancialmanagementjonathangalassi.rest.controllers;

import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.Paciente;
import com.pfmjg.personalfinancialmanagementjonathangalassi.services.PacienteServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/pacientes")
@Slf4j
public class PacienteController {
    @Autowired
    private PacienteServices pacienteServices;

    @GetMapping
    public ResponseEntity<List<Paciente>> findAll() {
        List<Paciente> pacienteList = pacienteServices.findAll();
        return ResponseEntity.ok().body(pacienteList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Paciente> finById(@PathVariable Integer id) {
        Paciente obj = pacienteServices.findbyId(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping(value = "/cadastrar-paciente")
    public ResponseEntity<Paciente> insertPaciente(@RequestBody Paciente pac) {
        pac = pacienteServices.insertPaciente(pac);
        return ResponseEntity.ok().body(pac);
    }

    @GetMapping(value = "/nome-{nomePaciente}")
    public ResponseEntity<List<String>> searchByName(@PathVariable String nomePaciente) {
        if (nomePaciente == null || nomePaciente.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        List<String> pac = pacienteServices.searchByName(nomePaciente);
        return new ResponseEntity<List<String>>(pac, HttpStatus.CREATED);
    }

    @PutMapping(value = "/editar-paciente-{id}")
    public ResponseEntity<Paciente> updatePaciente(@PathVariable Integer id, @RequestBody Paciente pac) {

        pac = pacienteServices.updatePaciente(id, pac);

        return new ResponseEntity<Paciente>(pac, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deletar-paciente-{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        pacienteServices.deleteById(id);
        return ResponseEntity.noContent().build();
    }




}
