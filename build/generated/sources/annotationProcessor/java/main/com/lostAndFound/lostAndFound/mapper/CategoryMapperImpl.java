package com.lostAndFound.lostAndFound.mapper;

import com.lostAndFound.lostAndFound.dto.category.CategoryResponseDto;
import com.lostAndFound.lostAndFound.dto.category.CreateCategoryDto;
import com.lostAndFound.lostAndFound.model.Category;
import java.time.format.DateTimeFormatter;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-01T12:30:11+0100",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.13.jar, environment: Java 17.0.14 (Ubuntu)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public Category toEntity(CreateCategoryDto dto) {
        if ( dto == null ) {
            return null;
        }

        Category.CategoryBuilder category = Category.builder();

        category.name( dto.name() );

        return category.build();
    }

    @Override
    public CategoryResponseDto toDto(Category category) {
        if ( category == null ) {
            return null;
        }

        String id = null;
        String name = null;
        String createdAt = null;
        String updatedAt = null;

        id = category.getId();
        name = category.getName();
        if ( category.getCreatedAt() != null ) {
            createdAt = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( category.getCreatedAt() );
        }
        if ( category.getUpdatedAt() != null ) {
            updatedAt = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( category.getUpdatedAt() );
        }

        CategoryResponseDto categoryResponseDto = new CategoryResponseDto( id, name, createdAt, updatedAt );

        return categoryResponseDto;
    }
}
