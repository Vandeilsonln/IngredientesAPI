package com.vandeilson.nobre.controleingredientes.service;

import com.vandeilson.nobre.controleingredientes.entity.Ingredientes;
import com.vandeilson.nobre.controleingredientes.exceptions.IngredienteAlreadyRegisteredException;
import com.vandeilson.nobre.controleingredientes.exceptions.IngredienteNotFoundException;
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

    public Optional<Ingredientes> getIngredientesById(Long id) throws IngredienteNotFoundException {
        verifyIfExists(id);
        return ingredientesRepository.findById(id);
    }

    public Optional<Ingredientes> getIngredientesByDescricao(String descricao){
        return ingredientesRepository.findByDescricao(descricao);
    }

    public void deleteIngrediente(Long id) throws IngredienteNotFoundException {
        verifyIfExists(id);
        ingredientesRepository.deleteById(id);
    }

    public Ingredientes updateIngrediente(Long id, Ingredientes ingrediente) throws IngredienteNotFoundException {
        verifyIfExists(id);
        ingrediente.setId_ingrediente(id);
        return ingredientesRepository.save(ingrediente);

    }

    private void verifyIfExists(Long id) throws IngredienteNotFoundException {
        ingredientesRepository.findById(id)
                .orElseThrow(() -> new IngredienteNotFoundException(id));
    }

    private void verifyIfIsAlreadyRegistered (Ingredientes ingrediente)throws IngredienteAlreadyRegisteredException {
        Optional<Ingredientes> optSavedIngrediente = ingredientesRepository.findById(ingrediente.getId_ingrediente());

        if (optSavedIngrediente.isPresent()) {
            throw new IngredienteAlreadyRegisteredException(ingrediente);
        }
    }
}
