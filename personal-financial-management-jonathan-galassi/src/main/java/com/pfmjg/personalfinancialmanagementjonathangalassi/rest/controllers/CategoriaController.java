package com.pfmjg.personalfinancialmanagementjonathangalassi.rest.controllers;

import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.Categoria;
import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.Paciente;
import com.pfmjg.personalfinancialmanagementjonathangalassi.services.CategoriaServices;
import com.pfmjg.personalfinancialmanagementjonathangalassi.services.PacienteServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/categorias")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoriaController {

    @Autowired
    private CategoriaServices categoriaServices;

    @GetMapping(value = "/listar")
    public ResponseEntity<List<Categoria>> findAll() {
        List<Categoria> categoriaList = categoriaServices.findAll();
        return ResponseEntity.ok().body(categoriaList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Categoria> finById(@PathVariable Integer id) {
        Categoria obj = categoriaServices.findbyId(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping(value = "/cadastrar-categoria")
    public ResponseEntity<Categoria> insertPaciente(@RequestBody @Valid Categoria cat) {
        cat = categoriaServices.insertCategoria(cat);
        return ResponseEntity.ok().body(cat);
    }

//    @GetMapping(value = "/nome-{nomePaciente}")
//    public ResponseEntity<List<String>> searchByName(@PathVariable String nomePaciente) {
//        if (nomePaciente == null || nomePaciente.trim().isEmpty()) {
//            return ResponseEntity.badRequest().build();
//        }
//        List<String> pac = pacienteServices.searchByName(nomePaciente);
//        return new ResponseEntity<List<String>>(pac, HttpStatus.CREATED);
//    }

    @PutMapping(value = "/editar-categoria-{id}")
    public ResponseEntity<Categoria> updatePaciente(@PathVariable Integer id, @RequestBody Categoria cat) {

        cat = categoriaServices.updateCategoria(id, cat);

        return new ResponseEntity<Categoria>(cat, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deletar-paciente-{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        categoriaServices.deleteById(id);
        return ResponseEntity.noContent().build();
    }

//    @GetMapping("/ids")
//    public List<Integer> getAllCategoriaIds() {
//        List<Categoria> categorias = cateegori.findAll();
//        return pacientes.stream()
//                .map(Paciente::getIdPaciente)
//                .collect(Collectors.toList());
//    }
}
