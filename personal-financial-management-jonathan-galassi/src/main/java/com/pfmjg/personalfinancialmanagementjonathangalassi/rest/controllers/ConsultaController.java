package com.pfmjg.personalfinancialmanagementjonathangalassi.rest.controllers;

import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.Categoria;
import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.Consulta;
import com.pfmjg.personalfinancialmanagementjonathangalassi.services.CategoriaServices;
import com.pfmjg.personalfinancialmanagementjonathangalassi.services.ConsultaServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/consultas")
@CrossOrigin(origins = "http://localhost:4200")
public class ConsultaController {

    @Autowired
    private ConsultaServices consultaServices;

    @GetMapping(value = "/listar")
    public ResponseEntity<List<Consulta>> findAll() {
        List<Consulta> consultasList = consultaServices.findAll();
        return ResponseEntity.ok().body(consultasList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Consulta> finById(@PathVariable Integer id) {
        Consulta obj = consultaServices.findbyId(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping(value = "/cadastrar-consulta")
    public ResponseEntity<Consulta> insertConsulta(@RequestBody @Valid Consulta cnslt) {
        cnslt = consultaServices.insertConsulta(cnslt);
        return ResponseEntity.ok().body(cnslt);
    }

//    @GetMapping(value = "/nome-{nomePaciente}")
//    public ResponseEntity<List<String>> searchByName(@PathVariable String nomePaciente) {
//        if (nomePaciente == null || nomePaciente.trim().isEmpty()) {
//            return ResponseEntity.badRequest().build();
//        }
//        List<String> pac = pacienteServices.searchByName(nomePaciente);
//        return new ResponseEntity<List<String>>(pac, HttpStatus.CREATED);
//    }

    @PutMapping(value = "/editar-consulta-{id}")
    public ResponseEntity<Consulta> updateConsulta(@PathVariable Integer id, @RequestBody Consulta cnslt) {

        cnslt = consultaServices.updateConsulta(id, cnslt);

        return new ResponseEntity<Consulta>(cnslt, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deletar-consulta-{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        consultaServices.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
