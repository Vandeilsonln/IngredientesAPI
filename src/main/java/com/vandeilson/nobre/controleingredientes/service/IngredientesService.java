package com.vandeilson.nobre.controleingredientes.service;

import com.vandeilson.nobre.controleingredientes.entity.Ingredientes;
import com.vandeilson.nobre.controleingredientes.repository.IngredientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientesService {

    @Autowired
    private IngredientesRepository ingredientesRepository;

    public Ingredientes saveIngrediente(Ingredientes ingrediente){
        return ingredientesRepository.save(ingrediente);
    }

    public List<Ingredientes> saveAllIngredientes(List<Ingredientes> ingredientes){
        return ingredientesRepository.saveAll(ingredientes);
    }

    public List<Ingredientes> getIngredientes(){
        return ingredientesRepository.findAll();
    }

    public Optional<Ingredientes> getIngredientesById(Long id){
        return ingredientesRepository.findById(id);
    }

    public Optional<Ingredientes> getIngredientesByDescricao(String descricao){
        return ingredientesRepository.findByDescricao(descricao);
    }

    public void deleteIngrediente(Long id){
        ingredientesRepository.deleteById(id);
    }

    public Ingredientes updateIngrediente(Ingredientes ingrediente){
        Ingredientes foundIngrediente = ingredientesRepository.findById(ingrediente.getId_ingrediente()).orElse(null);

        foundIngrediente.setId_ingrediente(ingrediente.getId_ingrediente());
        foundIngrediente.setDescricao(ingrediente.getDescricao());
        foundIngrediente.setPreco(ingrediente.getPreco());
        foundIngrediente.setVolumePeso(ingrediente.getVolumePeso());
        foundIngrediente.setType(ingrediente.getType());

        return ingredientesRepository.save(foundIngrediente);
    }
}
