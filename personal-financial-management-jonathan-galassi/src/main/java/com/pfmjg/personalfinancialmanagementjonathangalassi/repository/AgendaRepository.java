package com.pfmjg.personalfinancialmanagementjonathangalassi.repository;

import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.agenda.Agenda;
import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.dto.AgendaConsultaDTO;
import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.paciente.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Integer> {
    

    @Query("SELECT a FROM Agenda a JOIN FETCH a.paciente.idPaciente p WHERE a.idAgenda = :idAgenda")
    Agenda findAgendamentoWithPacienteDetails(@Param("idAgenda") Integer idAgenda);

    @Query("SELECT a FROM Agenda a WHERE a.paciente.idPaciente = :idPaciente")
    List<Agenda> findByPacienteId(@Param("idPaciente") Integer idPaciente);

    @Query("SELECT agd FROM Agenda agd WHERE agd.descricao = :descricao")
    Agenda findByDescription(@Param("descricao") String descricao);
}
