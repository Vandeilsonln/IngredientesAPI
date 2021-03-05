package com.vandeilson.nobre.controleingredientes.controller;

import com.vandeilson.nobre.controleingredientes.entity.Ingredientes;
import com.vandeilson.nobre.controleingredientes.service.IngredientesService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/ingrediente")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class IngredientesController {

    private final IngredientesService ingredientesService;

    @PostMapping("/addSingle")
    @ResponseStatus(HttpStatus.CREATED)
    public Ingredientes createIngrediente(@RequestBody Ingredientes ingrediente){
        return ingredientesService.saveIngrediente(ingrediente);
    }

    @PostMapping("/addMany")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Ingredientes> createAllIngredientes(@RequestBody List<Ingredientes> ingredientes){
        return ingredientesService.saveAllIngredientes(ingredientes);
    }

    @GetMapping("/all")
    public List<Ingredientes> getIngredientes(){
        return ingredientesService.getIngredientes();
    }

    @GetMapping("/byId/{id}")
    public Ingredientes getById(@PathVariable Long id){
        return ingredientesService.getIngredientesById(id).orElse(null);
    }

    @GetMapping("/byDescription/{descricao}")
    public Ingredientes getByDescricao(@PathVariable String descricao){
        return ingredientesService.getIngredientesByDescricao(descricao).orElse(null);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id){
        ingredientesService.deleteIngrediente(id);
    }

    @PutMapping("/update")
    public Ingredientes updateIngrediente(@RequestBody Ingredientes ingrediente){
        return  ingredientesService.updateIngrediente(ingrediente);
    }
}
