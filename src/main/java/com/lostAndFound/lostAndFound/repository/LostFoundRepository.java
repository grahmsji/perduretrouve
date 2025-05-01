package com.lostAndFound.lostAndFound.repository;

import com.lostAndFound.lostAndFound.model.LostFound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LostFoundRepository extends JpaRepository<LostFound, String> {
}
