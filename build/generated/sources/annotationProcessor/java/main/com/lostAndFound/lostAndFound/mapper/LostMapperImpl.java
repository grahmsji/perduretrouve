package com.lostAndFound.lostAndFound.mapper;

import com.lostAndFound.lostAndFound.dto.lost.CreateLostDto;
import com.lostAndFound.lostAndFound.dto.lost.LostResponseDto;
import com.lostAndFound.lostAndFound.model.Category;
import com.lostAndFound.lostAndFound.model.Lost;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-01T12:30:10+0100",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.13.jar, environment: Java 17.0.14 (Ubuntu)"
)
@Component
public class LostMapperImpl implements LostMapper {

    @Override
    public Lost toEntity(CreateLostDto dto) {
        if ( dto == null ) {
            return null;
        }

        Lost.LostBuilder lost = Lost.builder();

        lost.name( dto.name() );
        lost.color( dto.color() );
        lost.state( dto.state() );
        lost.description( dto.description() );
        lost.lostDate( dto.lostDate() );
        lost.lostPlace( dto.lostPlace() );
        lost.proof( dto.proof() );
        lost.loserFullName( dto.loserFullName() );
        lost.loserEmail( dto.loserEmail() );
        lost.loserIdentityDocument( dto.loserIdentityDocument() );

        return lost.build();
    }

    @Override
    public LostResponseDto toDto(Lost lost) {
        if ( lost == null ) {
            return null;
        }

        String categoryId = null;
        String categoryName = null;
        String id = null;
        String name = null;
        String code = null;
        String color = null;
        String state = null;
        String description = null;
        String picture = null;
        LocalDate lostDate = null;
        String lostPlace = null;
        String proof = null;
        String loserFullName = null;
        String loserEmail = null;
        String loserIdentityDocument = null;
        Date createdAt = null;
        Date updatedAt = null;

        categoryId = lostCategoryId( lost );
        categoryName = lostCategoryName( lost );
        id = lost.getId();
        name = lost.getName();
        code = lost.getCode();
        color = lost.getColor();
        state = lost.getState();
        description = lost.getDescription();
        picture = lost.getPicture();
        lostDate = lost.getLostDate();
        lostPlace = lost.getLostPlace();
        proof = lost.getProof();
        loserFullName = lost.getLoserFullName();
        loserEmail = lost.getLoserEmail();
        loserIdentityDocument = lost.getLoserIdentityDocument();
        if ( lost.getCreatedAt() != null ) {
            createdAt = Date.from( lost.getCreatedAt().toInstant( ZoneOffset.UTC ) );
        }
        if ( lost.getUpdatedAt() != null ) {
            updatedAt = Date.from( lost.getUpdatedAt().toInstant( ZoneOffset.UTC ) );
        }

        LostResponseDto lostResponseDto = new LostResponseDto( id, name, code, color, state, description, picture, lostDate, lostPlace, proof, loserFullName, loserEmail, loserIdentityDocument, categoryId, categoryName, createdAt, updatedAt );

        return lostResponseDto;
    }

    private String lostCategoryId(Lost lost) {
        Category category = lost.getCategory();
        if ( category == null ) {
            return null;
        }
        return category.getId();
    }

    private String lostCategoryName(Lost lost) {
        Category category = lost.getCategory();
        if ( category == null ) {
            return null;
        }
        return category.getName();
    }
}
