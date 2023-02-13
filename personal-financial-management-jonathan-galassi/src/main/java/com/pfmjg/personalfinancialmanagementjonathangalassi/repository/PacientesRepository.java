package com.pfmjg.personalfinancialmanagementjonathangalassi.repository;

import com.pfmjg.personalfinancialmanagementjonathangalassi.model.Pacientes;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PacientesRepository extends CrudRepository<Pacientes, Integer> {

    @Query(value = "select * from Pacientes p where c.nomePaciente like '%:nomePaciente", nativeQuery = true)
    List<Pacientes> findPacientesByName(@Param("nomePaciente") String nome);
}
