package com.pfmjg.modulos.agenda.controller;

import com.pfmjg.modulos.agenda.dto.AgendaFiltros;
import com.pfmjg.modulos.agenda.dto.AgendaRequest;
import com.pfmjg.modulos.agenda.dto.AgendaResponse;
import com.pfmjg.modulos.agenda.service.AgendaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/agenda")
public class AgendaController {

    public final AgendaService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AgendaResponse salvar(@RequestBody @Valid AgendaRequest request) {
        return service.salvar(request);
    }

    @PutMapping("/{id}/alterar")
    public AgendaResponse alterar(@PathVariable Integer id, @RequestBody @Valid AgendaRequest request) {
        return service.alterar(id, request);
    }

    @GetMapping
    public List<AgendaResponse> buscarTodos(AgendaFiltros filtros) {
        return service.buscarTodos(filtros);
    }

    @GetMapping("/{id}/buscar")
    public AgendaResponse buscarPorId(@PathVariable Integer id) {
        return AgendaResponse.of(service.buscarPorId(id));
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
