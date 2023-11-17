package com.pfmjg.modulos.agendador;

import com.pfmjg.modulos.consulta.service.ConsultaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@RequiredArgsConstructor
public class Agendador {

    private final ConsultaService service;

    private static final String TIME_ZONE = "America/Sao_Paulo";
    private static final String AS_ONZE_HORAS = "0 0 23 * * *";

    private static final String AS_ONZE_HORAS_E_MEIA = "0 30 23 * * *";

    @Scheduled(cron = AS_ONZE_HORAS, zone = TIME_ZONE)
    public void atualizarStatusDeFaltas() {
        log.info("Inicia rotina que atualiza situação das consultas para falta quando a data posterior a hoje");
        service.atribuirFalta();
        log.info("Finaliza rotina que atualiza situação das consultas para falta quando a data posterior a hoje");
    }

    @Scheduled(cron = AS_ONZE_HORAS_E_MEIA, zone = TIME_ZONE)
    public void atualizarStatusDeFinalizados() {
        log.info("Inicia rotina que atualiza situação das consultas em andamento para finalizado quando a data posterior a hoje");
        service.finalizarTodasConsultasPassadas();
        log.info("Finaliza rotina que atualiza situação das consultas em andamento para finalizado quando a data posterior a hoje");
    }
}
