package com.lostAndFound.lostAndFound.dto.category;

import java.util.List;

public record CategoryResponseDto(
        String id,
        String name,
        String createdAt,
        String updatedAt
) {}

