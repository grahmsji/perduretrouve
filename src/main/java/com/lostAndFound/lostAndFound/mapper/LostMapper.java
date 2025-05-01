package com.lostAndFound.lostAndFound.mapper;

import com.lostAndFound.lostAndFound.dto.lost.CreateLostDto;
import com.lostAndFound.lostAndFound.dto.lost.LostResponseDto;
import com.lostAndFound.lostAndFound.model.Lost;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LostMapper {
    Lost toEntity(CreateLostDto dto);

    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "categoryName", source = "category.name")
    LostResponseDto toDto(Lost lost);
}
