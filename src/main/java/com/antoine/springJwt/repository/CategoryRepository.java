package com.antoine.springJwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.antoine.springJwt.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    //List<Category> findByCategoryId(Integer CategoryId);
}
