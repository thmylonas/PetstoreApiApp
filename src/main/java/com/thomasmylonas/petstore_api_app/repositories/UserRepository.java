package com.thomasmylonas.petstore_api_app.repositories;

import com.thomasmylonas.petstore_api_app.api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
