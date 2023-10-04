package com.pfmjg.personalfinancialmanagementjonathangalassi.domain.dao.impl;

import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.dao.PacientesDao;
import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.paciente.Paciente;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

public class PacientesDaoJDBC  implements PacientesDao {

    private Connection conn;

    public PacientesDaoJDBC(Connection conn) {
        this.conn = conn;
    }


    @Override
    public void insert(Paciente obj) {
    }

    @Override
    public void update(Paciente obj) {

    }

    @Override
    public void deleteById(Paciente obj) {

    }

    @Override
    public Paciente findById(Integer obj) {
//        PreparedStatement st = null;
//        ResultSet rs = null;
//
//        try {
//            st = conn.prepareStatement("SELECT pacientes.*, pacientes.nomePacientes as Usuário FROM pacientes" +
//                    "where pacientes.idPacientes = ?");
//        }

        return null;
    }

    @Override
    public Paciente findByName(String obj) {
        return null;
    }

    @Override
    public Paciente findByBirthday(Date obj) {
        return null;
    }

    @Override
    public List<Paciente> findAllById() {
        return null;
    }

    @Override
    public List<Paciente> findAllByName() {
        return null;
    }
}
