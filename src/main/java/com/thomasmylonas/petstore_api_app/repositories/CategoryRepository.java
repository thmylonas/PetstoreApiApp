package com.thomasmylonas.petstore_api_app.repositories;

import com.thomasmylonas.petstore_api_app.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findByName(String name);
}
