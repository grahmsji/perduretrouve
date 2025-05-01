package com.lostAndFound.lostAndFound.service;

import com.lostAndFound.lostAndFound.dto.found.CreateFoundDto;
import com.lostAndFound.lostAndFound.dto.found.FoundResponseDto;
import com.lostAndFound.lostAndFound.mapper.FoundMapper;
import com.lostAndFound.lostAndFound.model.Category;
import com.lostAndFound.lostAndFound.model.Found;
import com.lostAndFound.lostAndFound.repository.CategoryRepository;
import com.lostAndFound.lostAndFound.repository.FoundRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FoundService {
    private final FoundRepository foundRepository;
    private final CategoryRepository categoryRepository;
    private final FoundMapper foundMapper;

    @Value("${upload.directory:uploads}")
    private String uploadDir;

    public List<FoundResponseDto> getAllFound() {
        return foundRepository.findAll()
                .stream()
                .map(foundMapper::toDto)
                .collect(Collectors.toList());
    }

    public FoundResponseDto create(CreateFoundDto dto, MultipartFile foundPicture) throws IOException {
        Found found = foundMapper.toEntity(dto);

        if (foundPicture != null && !foundPicture.isEmpty()) {
            String fileName = UUID.randomUUID() + "-" + foundPicture.getOriginalFilename();
            File uploadDirectory = new File(uploadDir);
            System.out.println("File path is: " + uploadDirectory);
            if (!uploadDirectory.exists()) {
                System.out.println("The directory " + uploadDirectory + " don't exist we trying to create a new one" );
                uploadDirectory.mkdirs();
            }

            File dest = new File(uploadDirectory, fileName);
            System.out.println("Saving file to: " + dest.getAbsolutePath());

            foundPicture.transferTo(dest);
            found.setPicture(fileName);

        }
        Category category = categoryRepository.findById(dto.categoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        found.setCategory(category);

        Found founded = foundRepository.save(found);
        return foundMapper.toDto(founded);
    }
    public FoundResponseDto getFoundByCode(String code) {
        Found found = foundRepository.findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException("Found item not found with code: " + code));
        return foundMapper.toDto(found);
    }
    public List<FoundResponseDto> getByMatchingCategoryAndDate(LocalDate lostDate, String categoryId) {
        List<Found> foundObjects = foundRepository
                .findByCategoryIdAndFoundDateGreaterThanEqual(categoryId, lostDate);
        return foundObjects.stream()
                .map(foundMapper::toDto)
                .collect(Collectors.toList());
    }
}
