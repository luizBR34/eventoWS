package com.eventoWS.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eventoWS.model.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, String> {

	UserEntity findByUserName(String username);
}
