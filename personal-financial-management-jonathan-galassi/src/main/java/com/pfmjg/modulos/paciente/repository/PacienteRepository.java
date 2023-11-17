package com.pfmjg.modulos.paciente.repository;

import com.pfmjg.modulos.paciente.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer>,
        QuerydslPredicateExecutor<Paciente> {

    boolean existsByCpf(String cpf);

    boolean existsByCpfAndIdNot(String cpf, Integer id);
}
