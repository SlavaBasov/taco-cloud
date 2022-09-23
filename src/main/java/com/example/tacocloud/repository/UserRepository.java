package com.example.tacocloud.repository;

import com.example.tacocloud.model.Usr;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<Usr, Long> {

  Usr findByUsername(String username);
}
