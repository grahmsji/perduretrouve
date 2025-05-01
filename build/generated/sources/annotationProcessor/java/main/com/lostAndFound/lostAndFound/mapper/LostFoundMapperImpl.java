package com.lostAndFound.lostAndFound.mapper;

import com.lostAndFound.lostAndFound.dto.lostfound.CreateLostFoundDto;
import com.lostAndFound.lostAndFound.dto.lostfound.LostFoundResponseDto;
import com.lostAndFound.lostAndFound.model.LostFound;
import java.time.format.DateTimeFormatter;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-01T12:30:11+0100",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.13.jar, environment: Java 17.0.14 (Ubuntu)"
)
@Component
public class LostFoundMapperImpl implements LostFoundMapper {

    @Override
    public LostFound toEntity(CreateLostFoundDto dto) {
        if ( dto == null ) {
            return null;
        }

        LostFound.LostFoundBuilder lostFound = LostFound.builder();

        lostFound.status( dto.status() );

        return lostFound.build();
    }

    @Override
    public LostFoundResponseDto toDto(LostFound lostFound) {
        if ( lostFound == null ) {
            return null;
        }

        String id = null;
        String status = null;
        String createdAt = null;
        String updatedAt = null;

        id = lostFound.getId();
        status = lostFound.getStatus();
        if ( lostFound.getCreatedAt() != null ) {
            createdAt = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( lostFound.getCreatedAt() );
        }
        if ( lostFound.getUpdatedAt() != null ) {
            updatedAt = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( lostFound.getUpdatedAt() );
        }

        String lostId = null;
        String foundId = null;

        LostFoundResponseDto lostFoundResponseDto = new LostFoundResponseDto( id, lostId, foundId, status, createdAt, updatedAt );

        return lostFoundResponseDto;
    }
}
