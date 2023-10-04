package com.pfmjg.personalfinancialmanagementjonathangalassi.domain.dao;

import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.paciente.Paciente;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@RequestMapping("/paciente")
public interface PacientesDao {

    void insert(Paciente obj);
    void update(Paciente obj);
    void deleteById(Paciente obj);
    Paciente findById(Integer obj);
    Paciente findByName(String obj);
    Paciente findByBirthday(Date obj);

    List<Paciente> findAllById();
    List<Paciente>  findAllByName();
}
