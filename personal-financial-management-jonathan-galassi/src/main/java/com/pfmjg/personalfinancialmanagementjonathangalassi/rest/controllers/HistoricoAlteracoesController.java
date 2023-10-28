package com.pfmjg.personalfinancialmanagementjonathangalassi.rest.controllers;

import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.historicoalteracoes.HistoricoAlteracoes;
import com.pfmjg.personalfinancialmanagementjonathangalassi.services.HistoricoAlteracoesServices;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historicos")
@CrossOrigin(origins = "http://localhost:4200")
public class HistoricoAlteracoesController {

    @Autowired
    private HistoricoAlteracoesServices historicoAlteracoesService;


    @PostMapping("/cadastrar")
    public ResponseEntity<HistoricoAlteracoes> cadastrarHistoricoAlteracoes(
            @RequestBody HistoricoAlteracoes historicoAlteracoes,
            @RequestParam("idPaciente") Integer idPaciente,
            @RequestParam("idControleCaixa") Integer idControleCaixa) {
        try {
            HistoricoAlteracoes novoHistorico = historicoAlteracoesService.insertHistoricoAlteracoes(
                    historicoAlteracoes, idPaciente, idControleCaixa);
            return new ResponseEntity<>(novoHistorico, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            // Lide com a exceção de paciente ou controle de caixa não encontrado
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            // Lide com outras exceções
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<HistoricoAlteracoes> atualizarHistoricoAlteracoes(@PathVariable Integer id, @RequestBody HistoricoAlteracoes historicoAlteracoes) {
        HistoricoAlteracoes historicoAtualizado = historicoAlteracoesService.updateHistoricoAlteracoes(id, historicoAlteracoes);
        return new ResponseEntity<>(historicoAtualizado, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistoricoAlteracoes> obterHistoricoAlteracoes(@PathVariable Integer id, @RequestBody HistoricoAlteracoes historicoAlteracoes) {
        HistoricoAlteracoes historico = historicoAlteracoesService.updateHistoricoAlteracoes(id, historicoAlteracoes);
        return new ResponseEntity<>(historico, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirHistoricoAlteracoes(@PathVariable Integer id) {
        historicoAlteracoesService.deleteHistoricoAlteracoes(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<HistoricoAlteracoes>> listarHistoricoAlteracoes() {
        List<HistoricoAlteracoes> historicos = historicoAlteracoesService.findAll();
        return new ResponseEntity<>(historicos, HttpStatus.OK);
    }

}
