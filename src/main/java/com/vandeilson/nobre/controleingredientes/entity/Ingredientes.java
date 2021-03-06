package com.vandeilson.nobre.controleingredientes.entity;

import com.vandeilson.nobre.controleingredientes.enums.UnidadeMedidaEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_ingredientes")
public class Ingredientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_ingrediente;

    @Column(nullable = false, unique = true)
    private String descricao;

    @Column(nullable = false)
    private Float preco;

    @Column(nullable = false, name = "volume_peso")
    private int volumePeso;

    @Enumerated(EnumType.STRING)
    @Column(name = "unidade_medida", nullable = false)
    private UnidadeMedidaEnum type;

}
