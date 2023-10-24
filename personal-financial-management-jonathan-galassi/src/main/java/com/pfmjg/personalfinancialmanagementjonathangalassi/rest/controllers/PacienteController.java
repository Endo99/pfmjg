package com.pfmjg.personalfinancialmanagementjonathangalassi.rest.controllers;

import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.dto.PacienteDTO;
import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.agenda.Agenda;
import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.consulta.Consulta;
import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.paciente.Paciente;
import com.pfmjg.personalfinancialmanagementjonathangalassi.services.AgendaServices;
import com.pfmjg.personalfinancialmanagementjonathangalassi.services.ConsultaServices;
import com.pfmjg.personalfinancialmanagementjonathangalassi.services.PacienteServices;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/pacientes")
@CrossOrigin(origins = "http://localhost:4200")

@Slf4j
public class PacienteController {
    @Autowired
    private PacienteServices pacienteServices;
    @Autowired
    private ConsultaServices consultaServices;
    @Autowired
    private AgendaServices agendaServices;

    @GetMapping(value = "/listar")
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
    public ResponseEntity<Paciente> insertPaciente(@RequestBody @Valid Paciente pac) {
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

    @PutMapping(value = "/editar-paciente/{id}")
    public ResponseEntity<Paciente> updatePaciente(@PathVariable Integer id, @RequestBody Paciente pac) {

        pac = pacienteServices.updatePaciente(id, pac);

        return new ResponseEntity<Paciente>(pac, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deletar-paciente/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        pacienteServices.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/ids")
    public List<Integer> getAllPacienteIds() {
        List<Paciente> pacientes = pacienteServices.findAll();
        return pacientes.stream()
                .map(Paciente::getIdPaciente)
                .collect(Collectors.toList());
    }

    @GetMapping("/agendas/{idPaciente}")
    public ResponseEntity<List<Agenda>> getAgendasByPacienteId(@PathVariable Integer idPaciente) {
        List<Agenda> agendas = agendaServices.findAgendasByPacienteId(idPaciente);
        return ResponseEntity.ok(agendas);
    }

    @GetMapping("/consultas/{idPaciente}")
    public ResponseEntity<List<Consulta>> getConsultasByPacienteId(@PathVariable Integer idPaciente) {
        List<Consulta> consultas = consultaServices.findConsultasByPacienteId(idPaciente);
        return ResponseEntity.ok(consultas);
    }

    @GetMapping("/listar-cpfs")
    public ResponseEntity<List<String>> listarTodosOsCpfs() {
        List<String> cpfs = pacienteServices.listarTodosOsCpfs();
        return ResponseEntity.ok(cpfs);
    }

    @GetMapping("/detalhes/{cpf}")
    public ResponseEntity<PacienteDTO> getDetalhesDoPacientePorCPF(@PathVariable String cpf) {
        PacienteDTO pacienteDTO = pacienteServices.getDetalhesDoPacientePorCPF(cpf);
        if (pacienteDTO != null) {
            return ResponseEntity.ok(pacienteDTO);
        } else {
            return ResponseEntity.notFound().build(); // Ou outra resposta apropriada
        }
    }

}
