package com.lostAndFound.lostAndFound.controller;

import com.lostAndFound.lostAndFound.dto.category.CategoryResponseDto;
import com.lostAndFound.lostAndFound.dto.category.CreateCategoryDto;
import com.lostAndFound.lostAndFound.dto.category.UpdateCategoryDto;
import com.lostAndFound.lostAndFound.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admins/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Listing Logic
    @GetMapping("/")
    public String index(Model model, CsrfToken csrfToken) {
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("_csrf", csrfToken);
        return "admins/category/index";
    }

    // Create Logic
    @GetMapping("/create")
    public String showCreateForm(Model model, CsrfToken csrfToken) {
        model.addAttribute("category", new CreateCategoryDto(""));
        model.addAttribute("_csrf", csrfToken);
        return "admins/category/create";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("category") CreateCategoryDto dto,
                         BindingResult result, Model model,
                         CsrfToken csrfToken) {
        if (categoryService.categoryExistsByName(dto.name())) {
            result.rejectValue("name", "error.category", "Category already exists");
        }

        if (result.hasErrors()) {
            model.addAttribute("category", dto);
            model.addAttribute("_csrf", csrfToken);
            return "admins/category/create";
        }
        CategoryResponseDto createdCategory = categoryService.createCategory(dto);
        return "redirect:/admins/category/";
    }

    // Edit logic
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model, CsrfToken csrfToken) {
        CategoryResponseDto category = categoryService.getCategoryById(id);
        UpdateCategoryDto dto = new UpdateCategoryDto(category.id(), category.name());
        model.addAttribute("category", dto);
        model.addAttribute("_csrf", csrfToken);
        return "admins/category/edit";
    }
    @PostMapping("/edit/{id}")
    public String update(@PathVariable String id,
                         @Valid @ModelAttribute("category") UpdateCategoryDto dto,
                         BindingResult result, Model model, CsrfToken csrfToken) {

        if (categoryService.categoryExistsByName(dto.name())) {
            result.rejectValue("name", "error.category", "Category already exists");
        }

        if (result.hasErrors()) {
            model.addAttribute("_csrf", csrfToken);
            return "admins/category/edit";
        }

        categoryService.updateCategory(id, dto);
        return "redirect:/admins/category/";
    }

    // Delete Logic
    @DeleteMapping("/delete/{id}")
    public String deleteCategory(@PathVariable String id) {
        categoryService.deleteCategory(id);
        return "redirect:/admins/category/";
    }
}
