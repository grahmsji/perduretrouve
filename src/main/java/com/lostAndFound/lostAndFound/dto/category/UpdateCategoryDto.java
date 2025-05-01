package com.lostAndFound.lostAndFound.dto.category;

import jakarta.validation.constraints.NotBlank;

public record UpdateCategoryDto(
        String id,
        @NotBlank String name
) {}