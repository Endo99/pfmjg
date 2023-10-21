package com.pfmjg.personalfinancialmanagementjonathangalassi.repository;

import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.agenda.Agenda;
import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.dto.AgendaConsultaDTO;
import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.paciente.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AgendaRepository extends JpaRepository<Agenda, Integer> {
    

    @Query("SELECT a FROM Agenda a JOIN FETCH a.paciente.idPaciente p WHERE a.idAgenda = :idAgenda")
    Agenda findAgendamentoWithPacienteDetails(@Param("idAgenda") Integer idAgenda);

    @Query("SELECT a FROM Agenda a WHERE a.paciente.idPaciente = :idPaciente")
    List<Agenda> findByPacienteId(@Param("idPaciente") Integer idPaciente);
}
