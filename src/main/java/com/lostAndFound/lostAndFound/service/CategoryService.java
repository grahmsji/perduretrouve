package com.lostAndFound.lostAndFound.service;

import com.lostAndFound.lostAndFound.dto.category.CategoryResponseDto;
import com.lostAndFound.lostAndFound.dto.category.CreateCategoryDto;
import com.lostAndFound.lostAndFound.dto.category.UpdateCategoryDto;
import com.lostAndFound.lostAndFound.mapper.CategoryMapper;
import com.lostAndFound.lostAndFound.model.Category;
import com.lostAndFound.lostAndFound.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public List<CategoryResponseDto> getAllCategory() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList());
    }

    public CategoryResponseDto getCategoryById(String id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return categoryMapper.toDto(category);
    }

    public CategoryResponseDto createCategory(CreateCategoryDto dto) {
        if (categoryRepository.existsByName(dto.name())) {
            throw new RuntimeException("Category already exists");
        }
        try {
            Category savedCategory = categoryRepository.save(categoryMapper.toEntity(dto));
            return categoryMapper.toDto(savedCategory);
        } catch (Exception e) {
            System.out.println("Create Category Error: " + e);
            throw new RuntimeException("Category name must be unique");
        }
    }

    public CategoryResponseDto updateCategory(String id, UpdateCategoryDto dto) {
        Category categoryToUpdate = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category Not found"));
        categoryToUpdate.setName(dto.name());
        try {
            return categoryMapper.toDto(categoryRepository.save(categoryToUpdate));
        } catch (Exception e) {
            System.out.println("Create Category Error: " + e);
            throw new RuntimeException("Category name must be unique");
        }
    }

    public void deleteCategory(String id) {
        Category categoryToDelete = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category Not found"));
        categoryRepository.delete(categoryToDelete);
    }

    public boolean categoryExistsByName(String name) {
        return categoryRepository.existsByName(name);
    }
}
