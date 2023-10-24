package com.pfmjg.personalfinancialmanagementjonathangalassi.repository;

import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.historicoalteracoes.HistoricoAlteracoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricoAlteracoesRepository extends JpaRepository<HistoricoAlteracoes, Integer> {
}
