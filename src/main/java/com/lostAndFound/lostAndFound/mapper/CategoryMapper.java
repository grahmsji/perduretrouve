package com.lostAndFound.lostAndFound.mapper;

import com.lostAndFound.lostAndFound.dto.category.CategoryResponseDto;
import com.lostAndFound.lostAndFound.dto.category.CreateCategoryDto;
import com.lostAndFound.lostAndFound.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toEntity(CreateCategoryDto dto);
    CategoryResponseDto toDto(Category category);
}
