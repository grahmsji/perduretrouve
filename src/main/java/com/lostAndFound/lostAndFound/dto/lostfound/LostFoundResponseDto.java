package com.lostAndFound.lostAndFound.dto.lostfound;

public record LostFoundResponseDto(
        String id,
        String lostId,
        String foundId,
        String status,
        String createdAt,
        String updatedAt
) {}