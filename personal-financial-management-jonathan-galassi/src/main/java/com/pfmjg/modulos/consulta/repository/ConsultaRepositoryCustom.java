package com.pfmjg.modulos.consulta.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ConsultaRepositoryCustom {

    List<LocalTime> buscarHorariosPorAgendaId(Integer agendaId, LocalDate data);
}
