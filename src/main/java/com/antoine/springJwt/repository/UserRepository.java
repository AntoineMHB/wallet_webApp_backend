package com.antoine.springJwt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.antoine.springJwt.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

}
