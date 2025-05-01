package com.lostAndFound.lostAndFound.repository;

import com.lostAndFound.lostAndFound.model.Lost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface LostRepository extends JpaRepository<Lost, String> {
    Optional<Lost> findByCode(String code);
    List<Lost> findByCategoryIdAndLostDateLessThanEqual(String categoryId, LocalDate foundDate);

}
