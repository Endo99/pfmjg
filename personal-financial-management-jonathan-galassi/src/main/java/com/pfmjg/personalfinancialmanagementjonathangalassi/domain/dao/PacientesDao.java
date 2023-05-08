package com.pfmjg.personalfinancialmanagementjonathangalassi.domain.dao;

import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.Pacientes;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@RequestMapping("/paciente")
public interface PacientesDao {

    void insert(Pacientes obj);
    void update(Pacientes obj);
    void deleteById(Pacientes obj);
    Pacientes findById(Integer obj);
    Pacientes findByName(String obj);
    Pacientes findByBirthday(Date obj);

    List<Pacientes> findAllById();
    List<Pacientes>  findAllByName();
}
