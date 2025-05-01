package com.lostAndFound.lostAndFound.dto.lost;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.Date;

public record LostResponseDto(
        String id,
        String name,
        String code,
        String color,
        String state,
        String description,
        String picture,
        LocalDate lostDate,
        String lostPlace,
        String proof,
        String loserFullName,
        String loserEmail,
        String loserIdentityDocument,
        String categoryId,
        String categoryName,
        Date createdAt,
        Date updatedAt
) {}
