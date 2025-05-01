package com.lostAndFound.lostAndFound.dto.found;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.Date;

public record FoundResponseDto(
        String id,
        String name,
        String code,
        String color,
        String state,
        String description,
        String picture,
        LocalDate foundDate,
        String foundPlace,
        String proof,
        String depositPlace,
        String specialMentions,
        String founderFullName,
        String founderEmail,
        String founderIdentityDocument,
        String categoryId,
        String categoryName,
        Date createdAt,
        Date updatedAt
) {}