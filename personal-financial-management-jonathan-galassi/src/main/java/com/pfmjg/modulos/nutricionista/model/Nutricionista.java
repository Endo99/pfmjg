package com.pfmjg.modulos.nutricionista.model;

import com.pfmjg.modulos.categoria.model.Categoria;
import com.pfmjg.modulos.comum.enums.ESituacao;
import com.pfmjg.modulos.nutricionista.dto.NutricionistaRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.pfmjg.modulos.comum.util.StringUtil.removerCaracteresNaoNumericos;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"CPF"})})
public class Nutricionista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "CPF", unique = true)
    private String cpf;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(length = 20, name = "TELEFONE")
    private String telefone;

    @Column(name = "SITUACAO")
    @Enumerated(EnumType.STRING)
    private ESituacao situacao;

    @ManyToMany
    @JoinColumn(name = "ID")
    private List<Categoria> categorias;

    public static Nutricionista of(NutricionistaRequest request, List<Categoria> categorias) {
        return Nutricionista.builder()
                .cpf(removerCaracteresNaoNumericos(request.cpf()))
                .nome(request.nome())
                .descricao(request.descricao())
                .telefone(removerCaracteresNaoNumericos(request.telefone()))
                .situacao(ESituacao.ATIVO)
                .categorias(categorias)
                .build();
    }
}
