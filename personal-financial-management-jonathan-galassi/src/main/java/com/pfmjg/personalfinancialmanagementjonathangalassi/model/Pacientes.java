package com.pfmjg.personalfinancialmanagementjonathangalassi.model;

import jakarta.persistence.*;

import java.util.Date;


@Entity
public class Pacientes {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ", allocationSize = 1)
    //@Column(length = 40) // Colocar o tamanho da coluna;
    private Long idPaciente;
    @Column(length = 300)
    private String nomePaciente;

    @Column(length = 300)
    private String sobrenomePaciente;

    private Date dataDeNascimentoPaciente;

    @Column(length = 300)
    private String formaDePagamento;

    @Column(length = 300)
    private String tipoDeConsulta;

    private double valorDaConsulta;

    private Date mesesAcompanhado;

    private double quantiaPaga;

    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPacientes(Long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getSobrenomePaciente() {
        return sobrenomePaciente;
    }

    public void setSobrenomePaciente(String sobrenomePaciente) {
        this.sobrenomePaciente = sobrenomePaciente;
    }

    public Date getDataDeNascimentoPaciente() {
        return dataDeNascimentoPaciente;
    }

    public void setDataDeNascimentoPaciente(Date dataDeNascimentoPaciente) {
        this.dataDeNascimentoPaciente = dataDeNascimentoPaciente;
    }

    public String getFormaDePagamento() {
        return formaDePagamento;
    }

    public void setFormaDePagamento(String formaDePagamento) {
        this.formaDePagamento = formaDePagamento;
    }

    public String getTipoDeConsulta() {
        return tipoDeConsulta;
    }

    public void setTipoDeConsulta(String tipoDeConsulta) {
        this.tipoDeConsulta = tipoDeConsulta;
    }

    public double getValorDaConsulta() {
        return valorDaConsulta;
    }

    public void setValorDaConsulta(double valorDaConsulta) {
        this.valorDaConsulta = valorDaConsulta;
    }

    public Date getMesesAcompanhado() {
        return mesesAcompanhado;
    }

    public void setMesesAcompanhado(Date mesesAcompanhado) {
        this.mesesAcompanhado = mesesAcompanhado;
    }

    public double getQuantiaPaga() {
        return quantiaPaga;
    }

    public void setQuantiaPaga(double quantiaPaga) {
        this.quantiaPaga = quantiaPaga;
    }
}
