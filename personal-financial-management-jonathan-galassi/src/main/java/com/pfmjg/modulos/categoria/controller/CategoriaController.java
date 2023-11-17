package com.pfmjg.modulos.categoria.controller;

import com.pfmjg.modulos.categoria.dto.CategoriaRequest;
import com.pfmjg.modulos.categoria.dto.CategoriaResponse;
import com.pfmjg.modulos.categoria.service.CategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categoria")
public class CategoriaController {

    public final CategoriaService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoriaResponse salvar(@RequestBody @Valid CategoriaRequest request) {
        return service.salvar(request);
    }

    @PutMapping("/{id}/alterar")
    public CategoriaResponse alterar(@PathVariable Integer id, @RequestBody @Valid CategoriaRequest request) {
        return service.alterar(id, request);
    }

    @GetMapping
    public List<CategoriaResponse> buscarTodos() {
        return service.buscarTodos();
    }

    @GetMapping("/{id}/buscar")
    public CategoriaResponse buscarPorId(@PathVariable Integer id) {
        return CategoriaResponse.of(service.buscarPorId(id));
    }

    @DeleteMapping("/{id}/deletar")
    public void deletar(@PathVariable Integer id) {
        service.deletar(id);
    }
}
