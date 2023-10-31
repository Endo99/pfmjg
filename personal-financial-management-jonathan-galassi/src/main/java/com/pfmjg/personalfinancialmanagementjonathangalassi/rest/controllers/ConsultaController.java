package com.pfmjg.personalfinancialmanagementjonathangalassi.rest.controllers;

import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.dto.ConsultaCompletaDTO;
import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.agenda.Agenda;
import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.consulta.Consulta;
import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.dto.ConsultaDTO;
import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.paciente.Paciente;
import com.pfmjg.personalfinancialmanagementjonathangalassi.services.AgendaServices;
import com.pfmjg.personalfinancialmanagementjonathangalassi.services.ConsultaServices;
import com.pfmjg.personalfinancialmanagementjonathangalassi.services.PacienteServices;
import jakarta.validation.Valid;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/consultas")
@CrossOrigin(origins = "http://localhost:4200")
public class ConsultaController {

    @Autowired
    private ConsultaServices consultaServices;
//    @Autowired
//    private PacienteServices pacienteServices;
    @Autowired
    private AgendaServices agendaServices;

    @GetMapping(value = "/listar")
    public ResponseEntity<List<Consulta>> findAll() {
            List<Consulta> consultaList = consultaServices.findAll();
            return ResponseEntity.ok().body(consultaList);
    }


//    @PostMapping(value = "/cadastrar-consulta/{idPaciente}")
//    public ResponseEntity<Consulta> insertConsulta(@RequestBody @Valid Consulta cnslta,
//                                                   @RequestParam(name = "id") Integer idPaciente) {
//
//        try {
//            // Busque a agenda correspondente ao ID
//            Agenda agenda = agendaServices.findbyId(idAgenda);
//
//            // Verifique se a agenda foi encontrada
//            if (agenda == null) {
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            }
//
//            // Associe a consulta à agenda
//
//            // Salve a consulta no banco de dados
//            Consulta novaConsulta = consultaServices.insertConsulta(idAgenda, cnslta);
//
//            // Adicione a consulta à lista de consultas da agenda
//            agenda.getConsultasAgendas().add(novaConsulta);
//
//            // Atualize a agenda no banco de dados para refletir as mudanças na lista de consultas
//            agendaServices.updateAgendamentoa(idAgenda, agenda);
//
//            return new ResponseEntity<>(novaConsulta, HttpStatus.CREATED);
//        } catch (EntityNotFoundException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @PostMapping(value = "/cadastrar-consulta")
    public ResponseEntity<Consulta> cadConsulta(@RequestBody @Valid Consulta cnslta) {

        try {
            Consulta novaConsulta = consultaServices.insertConsulta(cnslta);
            return new ResponseEntity<>(novaConsulta, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
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

    @PutMapping(value = "/editar-consulta/{id}")
    public ResponseEntity<Consulta> updateConsulta(@PathVariable Integer id, @RequestBody Consulta cnslt) {

        cnslt = consultaServices.updateConsulta(id, cnslt);

        return new ResponseEntity<Consulta>(cnslt, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deletar-consulta/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        consultaServices.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filtro/{tipo}")
    public List<Consulta> findConsultasByTipo(@PathVariable String tipo) {
        return consultaServices.findConsultasByTipo(tipo);
    }

    @GetMapping("/ids")
    public List<Integer> getAllConsultaIds() {
        List<Consulta> consultas = consultaServices.findAll();
        return consultas.stream()
                .map(Consulta::getIdConsulta)
                .collect(Collectors.toList());
    }
}
