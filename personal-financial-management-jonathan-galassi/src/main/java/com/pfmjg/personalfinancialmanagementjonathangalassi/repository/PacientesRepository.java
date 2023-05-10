package com.pfmjg.personalfinancialmanagementjonathangalassi.repository;

import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.Pacientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Repository

public interface PacientesRepository extends JpaRepository<Pacientes, Integer> {

    @Query(value = "select p.nomePaciente from Pacientes p where lower(p.nomePaciente) like %:nomePaciente%")
    List<String> searchByName(@Param("nomePaciente") String nomePaciente);

    @Query(value = "select p.sobrenomePaciente from Pacientes p where p.sobrenomePaciente like %:sobrenome%")
    List<String> searchByLastname(@Param("sobrenome") String sobrenome);

//    @Query(value = "select p.nomePaciente, p.sobrenomePaciente, concat(p.nomePaciente, ' ', p.sobrenomePaciente) as Nome from Pacientes p where lower(p.nomePaciente) or lower(p.sobrenomePaciente) like lower('%:nomePaciente%') or lower('%:sobrenomePaciente%')")
//    List<String> searchByNameAndLastname(@Param("nomePaciente") String nomePaciente, @Param("sobrenomePaciente") String sobrenomePaciente);

//    @Query(value = "select p.idPaciente from Pacientes p where p.idPaciente = :idPaciente ")
//    List<Pacientes> deletePacientesByIdPaciente(@Param("idPaciente" Integer idPaciente);

}
