package com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.historicoalteracoes;

import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.financa.ControleCaixa;
import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.paciente.Paciente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class HistoricoAlteracoes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idHistoricoAlteracoes;

    @ManyToOne
    @JoinColumn(name = "idPaciente")
    private Paciente idPaciente;

    @ManyToOne
    @JoinColumn(name = "idControleCaixa")
    private ControleCaixa idControleCaixa;

    private Date dataAlteracao;
    private String hora;
    private Double quantidadePago;
    private Double valorConsulta;
    private String tipoPagamento;
    private String statusPagamentoMod;
    private Double valorTransacionado;

}
