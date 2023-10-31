package com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities;

import com.pfmjg.personalfinancialmanagementjonathangalassi.domain.entities.financa.ControleCaixa;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCategoria;

    @ManyToOne
    @JoinColumn(name = "id_controle")
    private ControleCaixa controleCaixa;

    @NotNull
    private String tipoCategoria;

    @NotNull
    private String descricao;
}
