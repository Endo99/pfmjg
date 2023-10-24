package com.pfmjg.personalfinancialmanagementjonathangalassi.rest.controllers;

import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.financa.ControleCaixa;
import com.pfmjg.personalfinancialmanagementjonathangalassi.services.ControleCaixaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/controles-caixas")
@CrossOrigin(origins = "http://localhost:4200")
public class ControleCaixaController {

    @Autowired
    private ControleCaixaServices controleCaixaServices;

    @GetMapping("/listar")
    public ResponseEntity<List<ControleCaixa>> listarControleCaixa() {
        List<ControleCaixa> controleCaixaList = controleCaixaServices.findAll();
        return ResponseEntity.ok().body(controleCaixaList);
    }

    @PostMapping("/associar/{idControleCaixa}/{idConta}")
    public ResponseEntity<Void> associarControleCaixaAConta(
            @PathVariable Integer idControleCaixa,
            @PathVariable Integer idConta) {
        controleCaixaServices.enrollControleCaixaInConta(idControleCaixa, idConta);
        return ResponseEntity.ok().build();
    }

//    @PostMapping("/cadastrar}")
//    public ResponseEntity<ControleCaixa> cadastrarControleCaixa(@PathVariable Integer idConta,
//            @RequestBody ControleCaixa controleCaixa) {
//        ControleCaixa novoControleCaixa = controleCaixaServices.insertControleCaixa(idConta, controleCaixa);
//        return new ResponseEntity<>(novoControleCaixa, HttpStatus.CREATED);
//    }

    @PostMapping("/cadastrar")
    public ResponseEntity<ControleCaixa> cadastrarControleCaixa(@RequestBody ControleCaixa controleCaixa) {
        ControleCaixa novoControleCaixa = controleCaixaServices.cadastrarControleCaixa(controleCaixa);
        return new ResponseEntity<>(novoControleCaixa, HttpStatus.CREATED);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<ControleCaixa> editarControleCaixa(
            @PathVariable Integer id,
            @RequestBody ControleCaixa controleCaixa) {
        ControleCaixa controleCaixaAtualizado = controleCaixaServices.updateControleCaixaa(id, controleCaixa);
        return ResponseEntity.ok().body(controleCaixaAtualizado);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarControleCaixa(@PathVariable Integer id) {
        controleCaixaServices.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
