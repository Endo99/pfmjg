package com.pfmjg.personalfinancialmanagementjonathangalassi.repository;

import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.agenda.Agenda;
import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.dto.AgendaConsultaDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AgendaRepository extends JpaRepository<Agenda, Integer> {

    @Query("SELECT new com.pfmjg.personalfinancialmanagementjonathangalassi.domain.dto.AgendaConsultaDTO(a.idAgenda, a.descricao) FROM Agenda a JOIN a.consultasAgendas c WHERE c.idConsulta = :idConsulta")
    public AgendaConsultaDTO findAgendaByConsultaId(@Param("idConsulta") Integer idConsulta);


}
