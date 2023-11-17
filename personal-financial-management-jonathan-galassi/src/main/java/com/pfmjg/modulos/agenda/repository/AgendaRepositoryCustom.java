package com.pfmjg.modulos.agenda.repository;

import com.pfmjg.modulos.agenda.model.Agenda;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;

import java.time.LocalDate;
import java.util.List;

public interface AgendaRepositoryCustom {

    List<Agenda> buscarAgendasPorData(LocalDate data);

    Tuple buscarDiasComAgenda(Predicate predicate);
}
