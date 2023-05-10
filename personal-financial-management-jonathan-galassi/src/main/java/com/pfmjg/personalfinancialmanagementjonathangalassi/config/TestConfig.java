package com.pfmjg.personalfinancialmanagementjonathangalassi.config;

import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.Pacientes;
import com.pfmjg.personalfinancialmanagementjonathangalassi.repository.PacientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.sql.Date;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private PacientesRepository pacientesRepository;

    @Override
    public void run(String... args) throws Exception {

//        String date1 = "1999-08-30";
//        String date2 = "1988-09-24";
//
//        Date datef1 = Date.valueOf(date1);
//        Date datef2 = Date.valueOf(date2);
//
//        Pacientes pc1 = new Pacientes(null, "Fabricio", "da Silva", datef1, "Cartão Crédito", "Presencial", 180.00, 3, 180.00);
//        Pacientes pc2 = new Pacientes(null, "Marcio", "Rodrigues", datef2, "Cartão Débito", "Online", 180.00, 2, 360.00);
//
//        pacientesRepository.saveAll(Arrays.asList(pc1, pc2));
    }
}
