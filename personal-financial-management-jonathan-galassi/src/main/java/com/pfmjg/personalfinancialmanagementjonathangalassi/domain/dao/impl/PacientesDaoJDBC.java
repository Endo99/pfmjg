package com.pfmjg.personalfinancialmanagementjonathangalassi.domain.dao.impl;

import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.dao.PacientesDao;
import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.Pacientes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;

public class PacientesDaoJDBC  implements PacientesDao {

    private Connection conn;

    public PacientesDaoJDBC(Connection conn) {
        this.conn = conn;
    }


    @Override
    public void insert(Pacientes obj) {
    }

    @Override
    public void update(Pacientes obj) {

    }

    @Override
    public void deleteById(Pacientes obj) {

    }

    @Override
    public Pacientes findById(Integer obj) {
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
    public Pacientes findByName(String obj) {
        return null;
    }

    @Override
    public Pacientes findByBirthday(Date obj) {
        return null;
    }

    @Override
    public List<Pacientes> findAllById() {
        return null;
    }

    @Override
    public List<Pacientes> findAllByName() {
        return null;
    }
}
