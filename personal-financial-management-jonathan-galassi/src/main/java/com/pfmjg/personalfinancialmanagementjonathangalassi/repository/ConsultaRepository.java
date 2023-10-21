package com.pfmjg.personalfinancialmanagementjonathangalassi.repository;

import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.agenda.Agenda;
import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.consulta.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, Integer> {

    @Query("SELECT c FROM Consulta c WHERE c.tipoConsulta = :tipo")
    List<Consulta> findByTipoConsulta(String tipo);

    @Query("SELECT c FROM Consulta c WHERE c.paciente.idPaciente = :idPaciente")
    List<Consulta> findByPacienteId(@Param("idPaciente") Integer idPaciente);
}
