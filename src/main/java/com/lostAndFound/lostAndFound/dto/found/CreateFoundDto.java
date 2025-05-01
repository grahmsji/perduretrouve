package com.lostAndFound.lostAndFound.dto.found;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Date;

public record CreateFoundDto(
        @NotBlank String name,
        @NotBlank String color,
        @NotBlank String state,
        @NotBlank String description,
        @NotNull LocalDate foundDate,
        @NotBlank String foundPlace,
        String proof,
        @NotBlank String depositPlace,
        String specialMentions,
        @NotBlank String founderFullName,
        @Email @NotBlank String founderEmail,
        String founderIdentityDocument,
        @NotBlank String categoryId
) {}