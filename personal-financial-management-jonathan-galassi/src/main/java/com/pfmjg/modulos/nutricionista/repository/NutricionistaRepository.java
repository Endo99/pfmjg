package com.pfmjg.modulos.nutricionista.repository;

import com.pfmjg.modulos.nutricionista.model.Nutricionista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface NutricionistaRepository extends JpaRepository<Nutricionista, Integer>,
        QuerydslPredicateExecutor<Nutricionista> {

    boolean existsByCpf(String cpf);

    boolean existsByCpfAndIdNot(String cpf, Integer id);
}
