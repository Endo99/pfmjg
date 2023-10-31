package com.pfmjg.personalfinancialmanagementjonathangalassi.repository;

import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.financa.ControleCaixa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ControleCaixaRepository extends JpaRepository<ControleCaixa, Integer> {

    @Query("SELECT c FROM ControleCaixa c JOIN FETCH c.contas")
    List<ControleCaixa> findAllWithContas();
}
