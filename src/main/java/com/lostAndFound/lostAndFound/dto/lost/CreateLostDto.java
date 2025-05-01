package com.lostAndFound.lostAndFound.dto.lost;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateLostDto(
        @NotBlank String name,
        @NotBlank String color,
        @NotBlank String state,
        @NotBlank String description,
        @NotNull LocalDate lostDate,
        @NotBlank String lostPlace,
        String proof,
        @NotBlank String loserFullName,
        @NotBlank @Email String loserEmail,
        String loserIdentityDocument,
        @NotBlank String categoryId

) {}
