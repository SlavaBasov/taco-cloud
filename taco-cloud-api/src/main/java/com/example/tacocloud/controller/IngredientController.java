package com.example.tacocloud.controller;

import com.example.model.model.Ingredient;
import com.example.model.repository.IngredientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/ingredients", produces = "application/json")
@CrossOrigin(origins = "http://localhost:8080")
public class IngredientController {

  private final IngredientRepository ingredientRepository;

  public IngredientController(IngredientRepository ingredientRepository) {
    this.ingredientRepository = ingredientRepository;
  }

  @GetMapping
  public Iterable<Ingredient> allIngredients() {
    return ingredientRepository.findAll();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Ingredient saveIngredient(@RequestBody Ingredient ingredient) {
    return ingredientRepository.save(ingredient);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
//  @PreAuthorize("#{hasRole('USER')}")
  public void deleteIngredient(@PathVariable("id") String ingredientId) {
    ingredientRepository.deleteById(ingredientId);
  }
}
