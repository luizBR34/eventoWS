package com.eventoWS.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eventoWS.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

	User findByUserName(String username);
}
