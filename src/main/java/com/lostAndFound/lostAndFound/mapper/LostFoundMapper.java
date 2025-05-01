package com.lostAndFound.lostAndFound.mapper;

import com.lostAndFound.lostAndFound.dto.lostfound.CreateLostFoundDto;
import com.lostAndFound.lostAndFound.dto.lostfound.LostFoundResponseDto;
import com.lostAndFound.lostAndFound.model.LostFound;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LostFoundMapper {
    LostFound toEntity(CreateLostFoundDto dto);
    LostFoundResponseDto toDto(LostFound lostFound);
}
