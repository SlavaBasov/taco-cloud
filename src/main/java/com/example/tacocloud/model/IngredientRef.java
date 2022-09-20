package com.example.tacocloud.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class IngredientRef implements Serializable {
  private static final long serialVersionUID = 1L;
  private final String ingredient;
}
