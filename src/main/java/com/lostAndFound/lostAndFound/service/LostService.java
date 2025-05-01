package com.lostAndFound.lostAndFound.service;

import com.lostAndFound.lostAndFound.dto.lost.CreateLostDto;
import com.lostAndFound.lostAndFound.dto.lost.LostResponseDto;
import com.lostAndFound.lostAndFound.mapper.LostMapper;
import com.lostAndFound.lostAndFound.model.Category;
import com.lostAndFound.lostAndFound.model.Lost;
import com.lostAndFound.lostAndFound.repository.CategoryRepository;
import com.lostAndFound.lostAndFound.repository.LostRepository;
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
public class LostService {

    private final LostRepository lostRepository;
    private final CategoryRepository categoryRepository;
    private final LostMapper lostMapper;

    @Value("${upload.directory:uploads}")
    private String uploadDir;

    public List<LostResponseDto> getAllLost() {
        return lostRepository.findAll()
                .stream()
                .map(lostMapper::toDto)
                .collect(Collectors.toList());
    }

    public LostResponseDto create(CreateLostDto dto, MultipartFile lostPicture) throws IOException {
        Lost lost = lostMapper.toEntity(dto);

        if (lostPicture != null && !lostPicture.isEmpty()) {
            String fileName = UUID.randomUUID() + "-" + lostPicture.getOriginalFilename();
            File uploadDirectory = new File(uploadDir);
            System.out.println("File path is: " + uploadDirectory);
            if (!uploadDirectory.exists()) {
                System.out.println("The directory " + uploadDirectory + " don't exist we trying to create a new one" );
                uploadDirectory.mkdirs();
            }

            File dest = new File(uploadDirectory, fileName);
            System.out.println("Saving file to: " + dest.getAbsolutePath());

            lostPicture.transferTo(dest);
            lost.setPicture(fileName);
        }

        Category category = categoryRepository.findById(dto.categoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        lost.setCategory(category);

        Lost saved = lostRepository.save(lost);
        return lostMapper.toDto(saved);
    }

    public LostResponseDto getLostByCode(String code) {
        Lost lost = lostRepository.findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException("Lost item not found with code: " + code));

        return lostMapper.toDto(lost);
    }

    public List<LostResponseDto> getByMatchingCategoryAndDate(LocalDate foundDate, String categoryId) {
        List<Lost> lostObjects = lostRepository.findByCategoryIdAndLostDateLessThanEqual(categoryId, foundDate);
        return lostObjects.stream()
                .map(lostMapper::toDto)
                .collect(Collectors.toList());
    }

}
