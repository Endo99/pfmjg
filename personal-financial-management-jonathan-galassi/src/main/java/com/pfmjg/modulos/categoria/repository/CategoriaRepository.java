package com.pfmjg.modulos.categoria.repository;

import com.pfmjg.modulos.categoria.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>,
        QuerydslPredicateExecutor<Categoria> {

    boolean existsByDescricao(String descricao);

    boolean existsByDescricaoAndIdNot(String descricao, Integer id);
}
