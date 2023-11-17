package com.pfmjg.modulos.paciente.controller;

import com.pfmjg.modulos.paciente.dto.PacienteFiltros;
import com.pfmjg.modulos.paciente.dto.PacienteRequest;
import com.pfmjg.modulos.paciente.dto.PacienteResponse;
import com.pfmjg.modulos.paciente.service.PacienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/paciente")
public class PacienteController {

    public final PacienteService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PacienteResponse salvar(@RequestBody @Valid PacienteRequest request) {
        return service.salvar(request);
    }

    @PutMapping("/{id}/alterar")
    public PacienteResponse alterar(@PathVariable Integer id, @RequestBody @Valid PacienteRequest request) {
        return service.alterar(id, request);
    }

    @GetMapping
    public List<PacienteResponse> buscarTodos(PacienteFiltros filtros) {
        return service.buscarTodos(filtros);
    }

    @GetMapping("/{id}/buscar")
    public PacienteResponse buscarPorId(@PathVariable Integer id) {
        return PacienteResponse.of(service.buscarPorId(id));
    }

    @PutMapping("/{id}/ativar")
    public void ativar(@PathVariable Integer id) {
        service.ativar(id);
    }

    @PutMapping("/{id}/inativar")
    public void inativar(@PathVariable Integer id) {
        service.inativar(id);
    }
}
