package com.pfmjg.personalfinancialmanagementjonathangalassi.repository;

import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AgendaRepository extends JpaRepository<Agenda, Integer> {

}
