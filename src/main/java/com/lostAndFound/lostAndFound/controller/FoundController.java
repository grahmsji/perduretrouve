package com.lostAndFound.lostAndFound.controller;

import com.lostAndFound.lostAndFound.dto.found.CreateFoundDto;
import com.lostAndFound.lostAndFound.dto.found.FoundResponseDto;
import com.lostAndFound.lostAndFound.dto.lost.LostResponseDto;
import com.lostAndFound.lostAndFound.dto.lostfound.LostFoundResponseDto;
import com.lostAndFound.lostAndFound.service.CategoryService;
import com.lostAndFound.lostAndFound.service.FoundService;
import com.lostAndFound.lostAndFound.service.LostAndFoundService;
import com.lostAndFound.lostAndFound.service.LostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/found")
public class FoundController {
    private final FoundService foundService;
    private final CategoryService categoryService;
    private final LostService lostService;
    private final LostAndFoundService lostAndFoundService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("foundList", foundService.getAllFound());
        return "public/found/index";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model, CsrfToken csrfToken) {
        model.addAttribute("found", new CreateFoundDto(
                "",
                "",
                "",
                "",
                LocalDate.now(),
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                ""
        ));
        model.addAttribute("_csrf", csrfToken);
        model.addAttribute("categories", categoryService.getAllCategory());
        return "public/found/create";
    }

    @PostMapping("/create")
    public String create(
            @ModelAttribute("found") @Valid CreateFoundDto dto,
            BindingResult bindingResult,
            @RequestParam("foundPicture")MultipartFile foundPicture,
            Model model,
            CsrfToken csrfToken
    ) throws IOException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.getAllCategory());
            model.addAttribute("_csrf", csrfToken);
            return "public/found/create";
        }
        FoundResponseDto saved = foundService.create(dto, foundPicture);
        model.addAttribute("code", saved.code());
        return "public/found/foundCode";
    }

    @GetMapping("/related-lost-request/{code}")
    public String relatedLostRequest(Model model, @PathVariable String code) {
        FoundResponseDto foundDto = foundService.getFoundByCode(code);

        List<LostResponseDto> relatedLostList = lostService.getByMatchingCategoryAndDate(foundDto.foundDate(), foundDto.categoryId());
        if (relatedLostList.isEmpty())
            return "redirect:/lost/";
        model.addAttribute("relatedLostList", relatedLostList);
        return "public/found/relatedLostRequest";
    }

    @GetMapping("/connect/{lostCode}")
    public String showFoundConnectForm(@PathVariable String lostCode, Model model, CsrfToken csrfToken) {
        model.addAttribute("lostCode", lostCode);
        model.addAttribute("_csrf", csrfToken);
        return "public/found/connectFound"; // your template
    }


    @PostMapping("/connect")
    public String connectFoundWithLost(@RequestParam String lostCode, @RequestParam String foundCode, Model model) {
        LostFoundResponseDto response = lostAndFoundService.createLostFound(lostCode, foundCode);
        model.addAttribute("connectedItem", response);
        return "public/found/connectSuccess";
    }
}
