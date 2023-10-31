package com.pfmjg.personalfinancialmanagementjonathangalassi.rest.controllers;

import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.classes.AssociacaoRequest;
import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.conta.Conta;
import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.financa.ControleCaixa;
import com.pfmjg.personalfinancialmanagementjonathangalassi.repository.ContaRepository;
import com.pfmjg.personalfinancialmanagementjonathangalassi.repository.ControleCaixaRepository;
import com.pfmjg.personalfinancialmanagementjonathangalassi.services.ControleCaixaServices;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/controles")
@CrossOrigin(origins = "http://localhost:4200")
public class ControleCaixaController {

    @Autowired
    private ControleCaixaServices controleCaixaServices;

    @Autowired
    private ControleCaixaRepository controleCaixaRepository;
    @Autowired
    private ContaRepository contaRepository;

    @GetMapping("/listar-with-account")
    public ResponseEntity<List<ControleCaixa>> listarControleCaixa() {
        List<ControleCaixa> controleCaixaList = controleCaixaRepository.findAllWithContas();
        return ResponseEntity.ok().body(controleCaixaList);
    }

    @GetMapping("/listar-todos")
    public ResponseEntity<List<ControleCaixa>> listarAllControle() {
        List<ControleCaixa> controleCaixaList = controleCaixaRepository.findAll();
        return ResponseEntity.ok().body(controleCaixaList);
    }

    @GetMapping("/id/{idControle}")
    public ResponseEntity<List<ControleCaixa>> getControleById(@PathVariable Integer idControle) {
            Optional<ControleCaixa> controleCaixa = controleCaixaRepository.findById(idControle);

            if (controleCaixa.isPresent()) {
                List<ControleCaixa> controleCaixaList = new ArrayList<>();
                controleCaixaList.add(controleCaixa.get());
                return ResponseEntity.ok().body(controleCaixaList);
            } else {
                return ResponseEntity.notFound().build();
            }
    }

    @PostMapping("/associar/{idControleCaixa}/{idConta}")
    public ResponseEntity<Void> associarControleCaixaAConta(
            @PathVariable Integer idControleCaixa,
            @PathVariable Integer idConta) {
        controleCaixaServices.enrollControleCaixaInConta(idControleCaixa, idConta);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<ControleCaixa> cadastrarControleCaixa(
            @RequestBody ControleCaixa controleCaixa) {
        ControleCaixa novoControleCaixa = controleCaixaRepository.save(controleCaixa);

        // Associar ControleCaixa às Contas
        for (Conta conta : controleCaixa.getContas()) {
            conta.getControleCaixa().add(novoControleCaixa);
            contaRepository.save(conta);
        }

        return ResponseEntity.ok(novoControleCaixa);
    }

    @PostMapping("/associar-conta")
    public ResponseEntity<Void> associarControleCaixaAConta(@RequestBody AssociacaoRequest request) {
        ControleCaixa controleCaixa = controleCaixaRepository.findById(request.getIdControleCaixa()).orElse(null);
        List<Conta> contas = contaRepository.findAllById(request.getIdContas());

        if (controleCaixa != null && !contas.isEmpty()) {
            controleCaixa.getContas().addAll(contas);
            for (Conta conta : contas) {
                conta.getControleCaixa().add(controleCaixa);
            }

            controleCaixaRepository.save(controleCaixa);
            contaRepository.saveAll(contas);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
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
