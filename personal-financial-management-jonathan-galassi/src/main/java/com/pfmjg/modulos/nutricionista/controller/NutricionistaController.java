package com.pfmjg.modulos.nutricionista.controller;

import com.pfmjg.modulos.categoria.service.CategoriaService;
import com.pfmjg.modulos.nutricionista.dto.NutricionistaFiltros;
import com.pfmjg.modulos.nutricionista.dto.NutricionistaRequest;
import com.pfmjg.modulos.nutricionista.dto.NutricionistaResponse;
import com.pfmjg.modulos.nutricionista.service.NutricionistaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/nutricionista")
public class NutricionistaController {

    public final NutricionistaService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NutricionistaResponse salvar(@RequestBody @Valid NutricionistaRequest request) {
        return service.salvar(request);
    }

    @PutMapping("/{id}/alterar")
    public NutricionistaResponse alterar(@PathVariable Integer id, @RequestBody @Valid NutricionistaRequest request) {
        return service.alterar(id, request);
    }

    @GetMapping
    public List<NutricionistaResponse> buscarTodos(NutricionistaFiltros filtros) {
        return service.buscarTodos(filtros);
    }

    @GetMapping("/{id}/buscar")
    public NutricionistaResponse buscarPorId(@PathVariable Integer id) {
        return NutricionistaResponse.of(service.buscarPorId(id));
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
