package com.pfmjg.modulos.agenda.repository;

import com.pfmjg.modulos.agenda.model.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Integer>,
        AgendaRepositoryCustom, QuerydslPredicateExecutor<Agenda> {
}
