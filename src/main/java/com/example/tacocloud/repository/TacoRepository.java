package com.example.tacocloud.repository;

import com.example.tacocloud.model.Taco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TacoRepository extends CrudRepository<Taco, Long> {
//  Iterable<Taco> findAll(PageRequest pag eRequest);
}
