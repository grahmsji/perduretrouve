package com.lostAndFound.lostAndFound.mapper;

import com.lostAndFound.lostAndFound.dto.found.CreateFoundDto;
import com.lostAndFound.lostAndFound.dto.found.FoundResponseDto;
import com.lostAndFound.lostAndFound.model.Category;
import com.lostAndFound.lostAndFound.model.Found;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-01T12:30:11+0100",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.13.jar, environment: Java 17.0.14 (Ubuntu)"
)
@Component
public class FoundMapperImpl implements FoundMapper {

    @Override
    public Found toEntity(CreateFoundDto dto) {
        if ( dto == null ) {
            return null;
        }

        Found.FoundBuilder found = Found.builder();

        found.name( dto.name() );
        found.color( dto.color() );
        found.state( dto.state() );
        found.description( dto.description() );
        found.foundDate( dto.foundDate() );
        found.foundPlace( dto.foundPlace() );
        found.proof( dto.proof() );
        found.depositPlace( dto.depositPlace() );
        found.specialMentions( dto.specialMentions() );
        found.founderFullName( dto.founderFullName() );
        found.founderEmail( dto.founderEmail() );
        found.founderIdentityDocument( dto.founderIdentityDocument() );

        return found.build();
    }

    @Override
    public FoundResponseDto toDto(Found found) {
        if ( found == null ) {
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
        LocalDate foundDate = null;
        String foundPlace = null;
        String proof = null;
        String depositPlace = null;
        String specialMentions = null;
        String founderFullName = null;
        String founderEmail = null;
        String founderIdentityDocument = null;
        Date createdAt = null;
        Date updatedAt = null;

        categoryId = foundCategoryId( found );
        categoryName = foundCategoryName( found );
        id = found.getId();
        name = found.getName();
        code = found.getCode();
        color = found.getColor();
        state = found.getState();
        description = found.getDescription();
        picture = found.getPicture();
        foundDate = found.getFoundDate();
        foundPlace = found.getFoundPlace();
        proof = found.getProof();
        depositPlace = found.getDepositPlace();
        specialMentions = found.getSpecialMentions();
        founderFullName = found.getFounderFullName();
        founderEmail = found.getFounderEmail();
        founderIdentityDocument = found.getFounderIdentityDocument();
        if ( found.getCreatedAt() != null ) {
            createdAt = Date.from( found.getCreatedAt().toInstant( ZoneOffset.UTC ) );
        }
        if ( found.getUpdatedAt() != null ) {
            updatedAt = Date.from( found.getUpdatedAt().toInstant( ZoneOffset.UTC ) );
        }

        FoundResponseDto foundResponseDto = new FoundResponseDto( id, name, code, color, state, description, picture, foundDate, foundPlace, proof, depositPlace, specialMentions, founderFullName, founderEmail, founderIdentityDocument, categoryId, categoryName, createdAt, updatedAt );

        return foundResponseDto;
    }

    private String foundCategoryId(Found found) {
        Category category = found.getCategory();
        if ( category == null ) {
            return null;
        }
        return category.getId();
    }

    private String foundCategoryName(Found found) {
        Category category = found.getCategory();
        if ( category == null ) {
            return null;
        }
        return category.getName();
    }
}
