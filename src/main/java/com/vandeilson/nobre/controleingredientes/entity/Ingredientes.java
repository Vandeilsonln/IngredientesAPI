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

    @Column
    private String descricao;

    @Column
    private Float preco;

    @Column(name = "volume_peso")
    private int volumePeso;

    @Column(name = "unidade_medida")
    private String type;

}
