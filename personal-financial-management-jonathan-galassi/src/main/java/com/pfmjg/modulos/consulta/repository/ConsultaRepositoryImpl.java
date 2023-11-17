package com.pfmjg.modulos.consulta.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static com.pfmjg.modulos.consulta.model.QConsulta.consulta;

@Repository
@RequiredArgsConstructor
public class ConsultaRepositoryImpl implements ConsultaRepositoryCustom {

    private final EntityManager entityManager;

    @Override
    public List<LocalTime> buscarHorariosPorAgendaId(Integer agendaId, LocalDate data) {
        return new JPAQueryFactory(entityManager)
                .select(consulta.horaInicial)
                .from(consulta)
                .where(consulta.agenda.id.eq(agendaId)
                        .and(consulta.data.eq(data)))
                .fetch();
    }
}
