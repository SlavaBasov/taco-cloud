package com.example.tacocloud.converter;

import com.example.model.model.Ingredient;
import com.example.model.repository.IngredientRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component

public class IngredientByIdConverter implements Converter<String, Ingredient> {

  private final IngredientRepository ingredientRepository;

  public IngredientByIdConverter(IngredientRepository ingredientRepository) {
    this.ingredientRepository = ingredientRepository;
  }

  @Override
  public Ingredient convert(String id) {
    return ingredientRepository.findById(id).orElse(null);
  }
}
