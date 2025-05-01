package com.lostAndFound.lostAndFound.repository;

import com.lostAndFound.lostAndFound.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    boolean existsByName(String name);
}
