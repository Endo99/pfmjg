package com.pfmjg.personalfinancialmanagementjonathangalassi.rest.controllers;

import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.dto.BuscaCPFDTO;
import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.dto.DadosRelacionadosDTO;
import com.pfmjg.personalfinancialmanagementjonathangalassi.services.DadosRelacionadosService;
import com.pfmjg.personalfinancialmanagementjonathangalassi.services.PacienteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DadosRelacionadosController {

    @Autowired
    private DadosRelacionadosService dadosRelacionadosService;



    @GetMapping("/dados-relacionados")
    public ResponseEntity<List<DadosRelacionadosDTO>> obterDadosRelacionados() {
        List<DadosRelacionadosDTO> dadosRelacionados = dadosRelacionadosService.buscarDadosRelacionados();
        return ResponseEntity.ok(dadosRelacionados);
    }

    @GetMapping("/listar-cpf-nome")
    public ResponseEntity<List<BuscaCPFDTO>> listarCpfNomes() {
        List<BuscaCPFDTO> cpfNomes = dadosRelacionadosService.findAllCpfNomes();
        return ResponseEntity.ok(cpfNomes);
    }
}
