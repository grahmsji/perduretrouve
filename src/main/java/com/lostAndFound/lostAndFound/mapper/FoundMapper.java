package com.lostAndFound.lostAndFound.mapper;

import com.lostAndFound.lostAndFound.dto.found.CreateFoundDto;
import com.lostAndFound.lostAndFound.dto.found.FoundResponseDto;
import com.lostAndFound.lostAndFound.model.Found;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FoundMapper {
    Found toEntity(CreateFoundDto dto);

    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "categoryName", source = "category.name")
    FoundResponseDto toDto(Found found);
}
