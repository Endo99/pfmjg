package com.pfmjg.modulos.agenda.repository;

import com.pfmjg.modulos.agenda.model.Agenda;
import com.pfmjg.modulos.comum.enums.ESituacao;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import static com.pfmjg.modulos.agenda.model.QAgenda.agenda;

@Repository
@RequiredArgsConstructor
public class AgendaRepositoryImpl implements AgendaRepositoryCustom {

    private final EntityManager entityManager;

    @Override
    public List<Agenda> buscarAgendasPorData(LocalDate data) {
        return new JPAQueryFactory(entityManager)
                .selectFrom(agenda)
                .where(agenda.dataInicial.before(data.plusDays(1))
                        .and(agenda.dataFinal.after(data.plusDays(-1)))
                        .and(agenda.situacao.eq(ESituacao.ATIVO)))
                .fetch();
    }

    @Override
    public Tuple buscarDiasComAgenda(Predicate predicate) {
        return new JPAQueryFactory(entityManager)
                .select(agenda.dataInicial.min(), agenda.dataFinal.max())
                .from(agenda)
                .where(predicate)
                .fetchOne();
    }
}
