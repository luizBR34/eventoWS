package com.eventoWS.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eventoWS.persistence.entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, String> {

	UserEntity findByUserName(String username);
}
