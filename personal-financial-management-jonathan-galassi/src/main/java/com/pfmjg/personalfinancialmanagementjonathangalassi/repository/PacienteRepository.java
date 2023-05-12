package com.pfmjg.personalfinancialmanagementjonathangalassi.repository;

import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {

    // Buscando por nome em qualquer posição.
    @Query("SELECT p.nomePaciente, p.sobrenomePaciente FROM Paciente p WHERE lower(p.nomePaciente) LIKE %:nomePaciente% OR lower(p.sobrenomePaciente) LIKE %:nomePaciente%")
    List<String> searchByName(@Param("nomePaciente") String nomePaciente);

    @Query(value = "select p.sobrenomePaciente from Paciente p where p.sobrenomePaciente like %:sobrenome%")
    List<String> searchByLastname(@Param("sobrenome") String sobrenome);

    Paciente findByNomePacienteAndAndSobrenomePacienteAndDataNascimentoPaciente(String nomePaciente, String sobrenome, Date dataNascimento);


//    @Query(value = "select p.nomePaciente, p.sobrenomePaciente, concat(p.nomePaciente, ' ', p.sobrenomePaciente) as Nome from Pacientes p where lower(p.nomePaciente) or lower(p.sobrenomePaciente) like lower('%:nomePaciente%') or lower('%:sobrenomePaciente%')")
//    List<String> searchByNameAndLastname(@Param("nomePaciente") String nomePaciente, @Param("sobrenomePaciente") String sobrenomePaciente);

//    @Query(value = "select p.idPaciente from Pacientes p where p.idPaciente = :idPaciente ")
//    List<Pacientes> deletePacientesByIdPaciente(@Param("idPaciente" Integer idPaciente);

}
