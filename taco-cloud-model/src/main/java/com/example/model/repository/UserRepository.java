package com.example.model.repository;

import com.example.model.model.Usr;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<Usr, Long> {

  Usr findByUsername(String username);
}
