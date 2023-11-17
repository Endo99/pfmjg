package com.pfmjg.modulos.consulta.controller;

import com.pfmjg.modulos.agenda.dto.AgendaResponse;
import com.pfmjg.modulos.consulta.dto.ConsultaFiltros;
import com.pfmjg.modulos.consulta.dto.ConsultaRequest;
import com.pfmjg.modulos.consulta.dto.ConsultaResponse;
import com.pfmjg.modulos.consulta.service.ConsultaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/consulta")
public class ConsultaController {

    public final ConsultaService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ConsultaResponse salvar(@RequestBody @Valid ConsultaRequest request) {
        return service.salvar(request);
    }

    @PutMapping("/{id}/alterar")
    public ConsultaResponse alterar(@PathVariable Integer id, @RequestBody @Valid ConsultaRequest request) {
        return service.alterar(id, request);
    }

    @GetMapping
    public List<ConsultaResponse> buscarTodos(ConsultaFiltros filtros) {
        return service.buscarTodos(filtros);
    }

    @GetMapping("/{id}/buscar")
    public ConsultaResponse buscarPorId(@PathVariable Integer id) {
        return ConsultaResponse.of(service.buscarPorId(id));
    }

    @PutMapping("/{id}/checkIn")
    public void checkIn(@PathVariable Integer id) {
        service.checkIn(id);
    }

    @PutMapping("/{id}/finalizar")
    public void finalizar(@PathVariable Integer id) {
        service.finalizar(id);
    }

    @PutMapping("/{id}/cancelar")
    public void cancelar(@PathVariable Integer id) {
        service.cancelar(id);
    }

    @GetMapping("/dias-agenda")
    public List<LocalDate> diasAgenda() {
        return service.diasComAgenda();
    }

    @GetMapping("/{data}/list-agenda")
    public List<AgendaResponse> agendaPorData(@PathVariable LocalDate data) {
        return service.agendaPorData(data);
    }

    @GetMapping("/{id}/{data}/horarios-livres")
    public List<LocalTime> horariosLivres(@PathVariable Integer id,
                                          @PathVariable LocalDate data) {
        return service.horariosLivres(id, data);
    }
}
