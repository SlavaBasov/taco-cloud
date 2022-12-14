package com.example.tacocloud.config;

import com.example.model.model.Ingredient;
import com.example.model.model.Ingredient.Type;
import com.example.model.model.Taco;
import com.example.model.model.Usr;
import com.example.model.repository.IngredientRepository;
import com.example.model.repository.TacoRepository;
import com.example.model.repository.UserRepository;
import java.util.Arrays;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataConfig {

  @Bean
  public ApplicationRunner dataLoader(IngredientRepository repo, UserRepository userRepository,
      TacoRepository tacoRepository, PasswordEncoder encoder) {
    return args -> {
      userRepository.save(new Usr("testUser", encoder.encode("123"), "qwe", "qwe",
          "qwe", "qwe", "qwe", "123"));
      Ingredient flourTortilla = new Ingredient(
          "FLTO", "Flour Tortilla", Type.WRAP);
      Ingredient cornTortilla = new Ingredient(
          "COTO", "Corn Tortilla", Type.WRAP);
      Ingredient groundBeef = new Ingredient(
          "GRBF", "Ground Beef", Type.PROTEIN);
      Ingredient carnitas = new Ingredient(
          "CARN", "Carnitas", Type.PROTEIN);
      Ingredient tomatoes = new Ingredient(
          "TMTO", "Diced Tomatoes", Type.VEGGIES);
      Ingredient lettuce = new Ingredient(
          "LETC", "Lettuce", Type.VEGGIES);
      Ingredient cheddar = new Ingredient(
          "CHED", "Cheddar", Type.CHEESE);
      Ingredient jack = new Ingredient(
          "JACK", "Monterrey Jack", Type.CHEESE);
      Ingredient salsa = new Ingredient(
          "SLSA", "Salsa", Type.SAUCE);
      Ingredient sourCream = new Ingredient(
          "SRCR", "Sour Cream", Type.SAUCE);
      repo.save(flourTortilla);
      repo.save(cornTortilla);
      repo.save(groundBeef);
      repo.save(carnitas);
      repo.save(tomatoes);
      repo.save(lettuce);
      repo.save(cheddar);
      repo.save(jack);
      repo.save(salsa);
      repo.save(sourCream);
      Taco taco1 = new Taco();
      taco1.setName("Carnivore");
      taco1.setIngredients(Arrays.asList(
          flourTortilla, groundBeef, carnitas,
          sourCream, salsa, cheddar));
      tacoRepository.save(taco1);
      Taco taco2 = new Taco();
      taco2.setName("Bovine Bounty");
      taco2.setIngredients(Arrays.asList(
          cornTortilla, groundBeef, cheddar,
          jack, sourCream));
      tacoRepository.save(taco2);
      Taco taco3 = new Taco();
      taco3.setName("Veg-Out");
      taco3.setIngredients(Arrays.asList(
          flourTortilla, cornTortilla, tomatoes,
          lettuce, salsa));
      tacoRepository.save(taco3);
    };
  }
}
