package com.pfmjg.modulos.consulta.repository;

import com.pfmjg.modulos.agenda.model.Agenda;
import com.pfmjg.modulos.consulta.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Integer>,
        ConsultaRepositoryCustom, QuerydslPredicateExecutor<Consulta> {

    List<Consulta> findByAgendaAndData(Agenda agenda, LocalDate data);
}
