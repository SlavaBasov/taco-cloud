package com.example.tacocloud.controller;

import com.example.model.model.Taco;
import com.example.model.repository.TacoRepository;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/tacos", produces = "application/json")
@CrossOrigin(origins = "http://tacocloud:8080")
public class TacoController {

  private final TacoRepository tacoRepository;

  public TacoController(TacoRepository tacoRepository) {
    this.tacoRepository = tacoRepository;
  }

  @GetMapping(params = "recent")
  public Iterable<Taco> recentTacos(@PageableDefault(value = 12)
  @SortDefault(sort = "createdAt", direction = Direction.DESC) Pageable pageable) {
    PageRequest page = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort());
    return tacoRepository.findAll(page).getContent();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
    Optional<Taco> optTaco = tacoRepository.findById(id);
    return optTaco.map(taco -> new ResponseEntity<>(taco, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
  }

  @PostMapping(consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public Taco createTaco(@RequestBody Taco taco) {
    return tacoRepository.save(taco);
  }
}
