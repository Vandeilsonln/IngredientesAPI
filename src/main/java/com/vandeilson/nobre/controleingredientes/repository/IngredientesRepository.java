package com.vandeilson.nobre.controleingredientes.repository;


import com.vandeilson.nobre.controleingredientes.entity.Ingredientes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IngredientesRepository extends JpaRepository<Ingredientes, Long> {

    Optional<Ingredientes> findByDescricao(String descricao);
}
