package com.lostAndFound.lostAndFound.dto.lostfound;

import jakarta.validation.constraints.NotBlank;

public record CreateLostFoundDto(
        @NotBlank String lostId,
        @NotBlank String foundId,
        String status
) {}