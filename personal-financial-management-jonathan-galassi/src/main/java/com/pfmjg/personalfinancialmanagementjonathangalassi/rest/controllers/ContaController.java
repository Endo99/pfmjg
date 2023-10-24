package com.pfmjg.personalfinancialmanagementjonathangalassi.rest.controllers;

import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.conta.Conta;
import com.pfmjg.personalfinancialmanagementjonathangalassi.services.ContaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contas")
@CrossOrigin(origins = "http://localhost:4200")
public class ContaController {

    private final ContaServices contaServices;

    @Autowired
    public ContaController(ContaServices contaServices) {
        this.contaServices = contaServices;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Conta>> findAll() {
        List<Conta> contas = contaServices.findAll();
        return ResponseEntity.ok().body(contas);
    }

    @PostMapping("/inserir")
    public ResponseEntity<Conta> insertConta(@PathVariable Integer idControleCaixa, @RequestBody Conta conta) {
        try {
            Conta novaConta = contaServices.insertConta(idControleCaixa, conta);
            return new ResponseEntity<>(novaConta, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Conta> cadastrarConta(@RequestBody Conta conta) {
        Conta novaConta = contaServices.cadastrarConta(conta);
        return new ResponseEntity<>(novaConta, HttpStatus.CREATED);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Conta> updateConta(@PathVariable Integer id, @RequestBody Conta conta) {
        try {
            Conta updatedConta = contaServices.updateConta(id, conta);
            return ResponseEntity.ok(updatedConta);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deleteConta(@PathVariable Integer id) {
        try {
            contaServices.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}