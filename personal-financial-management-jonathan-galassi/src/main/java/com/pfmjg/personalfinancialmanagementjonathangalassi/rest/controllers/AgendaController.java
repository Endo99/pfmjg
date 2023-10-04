package com.pfmjg.personalfinancialmanagementjonathangalassi.rest.controllers;

import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.agenda.Agenda;
import com.pfmjg.personalfinancialmanagementjonathangalassi.services.AgendaServices;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/agendamentos")
@CrossOrigin(origins = "http://localhost:4200")
public class AgendaController
{

    @Autowired
    private AgendaServices agendaServices;

    @GetMapping(value = "/listar")
    public ResponseEntity<List<Agenda>> findAll() {
        List<Agenda> agendaList = agendaServices.findAll();
        return ResponseEntity.ok().body(agendaList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Agenda> finById(@PathVariable Integer id) {
        Agenda obj = agendaServices.findbyId(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping(value = "/cadastrar-agenda")
    public ResponseEntity<Agenda> insertAgendamento(@RequestBody @Valid Agenda agd) {
        try {
            // Chame o serviço para criar a agenda associada ao paciente
            Agenda novaAgenda = agendaServices.criarAgenda(agd.getIdPaciente().getIdPaciente(), agd);

            // Retorne a nova agenda criada com o status 201 (Created)
            return new ResponseEntity<>(novaAgenda, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            // Se o paciente não for encontrado, retorne um erro 404 (Not Found)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            // Em caso de qualquer outra exceção, retorne um erro 500 (Internal Server Error)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping(value = "/nome-{nomePaciente}")
//    public ResponseEntity<List<String>> searchByName(@PathVariable String nomePaciente) {
//        if (nomePaciente == null || nomePaciente.trim().isEmpty()) {
//            return ResponseEntity.badRequest().build();
//        }
//        List<String> pac = pacienteServices.searchByName(nomePaciente);
//        return new ResponseEntity<List<String>>(pac, HttpStatus.CREATED);
//    }

    @PutMapping(value = "/editar-agenda/{id}")
    public ResponseEntity<Agenda> updateAgendamento(@PathVariable Integer id, @RequestBody Agenda agd) {

        agd = agendaServices.updateAgendamentoa(id, agd);

        return new ResponseEntity<Agenda>(agd, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deletar-agenda/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        agendaServices.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{idAgenda}")
    public ResponseEntity<Agenda> getAgendamento(@PathVariable Integer idAgendamento) {
        Agenda agenda = agendaServices.getAgendamentoById(idAgendamento);
        return ResponseEntity.ok(agenda);
    }

}
