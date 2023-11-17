package com.pfmjg.modulos.agendador.controller;

import com.pfmjg.modulos.consulta.service.ConsultaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/agendador")
public class AgendadorController {

    public final ConsultaService service;

    @PutMapping("/{id}/Falta")
    public void atribuirFalta() {
        service.atribuirFalta();
    }

    @PutMapping("/{id}/finalizado")
    public void finalizarTodasConsultasPassadas() {
        service.finalizarTodasConsultasPassadas();
    }
}
