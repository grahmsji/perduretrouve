package com.lostAndFound.lostAndFound.repository;

import com.lostAndFound.lostAndFound.model.Found;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface FoundRepository extends JpaRepository<Found, String> {
    Optional<Found> findByCode(String code);
    List<Found> findByCategoryIdAndFoundDateGreaterThanEqual(String categoryId, LocalDate lostDate);
}
