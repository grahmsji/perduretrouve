package com.lostAndFound.lostAndFound.controller;

import com.lostAndFound.lostAndFound.dto.found.FoundResponseDto;
import com.lostAndFound.lostAndFound.dto.lost.CreateLostDto;
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
@RequestMapping("/lost")
public class LostController {

    private final LostService lostService;
    private final CategoryService categoryService;
    private final FoundService foundService;
    private final LostAndFoundService lostAndFoundService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("lostList", lostService.getAllLost());
        return "public/lost/index";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model, CsrfToken csrfToken) {
        model.addAttribute("lost", new CreateLostDto(
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
                "")
        );
        model.addAttribute("_csrf", csrfToken);
        model.addAttribute("categories", categoryService.getAllCategory());
        return "public/lost/create";
    }

    @PostMapping("/create")
    public String create(
            @ModelAttribute("lost") @Valid CreateLostDto dto,
            BindingResult bindingResult,
            @RequestParam("lostPicture") MultipartFile lostPicture,
            Model model,
            CsrfToken csrfToken
    ) throws IOException {

        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.getAllCategory());
            model.addAttribute("_csrf", csrfToken);
            return "public/lost/create";
        }

        LostResponseDto saved = lostService.create(dto, lostPicture);
        model.addAttribute("code", saved.code());
        return "public/lost/lostCode";
    }

    @GetMapping("/related-found-request/{code}")
    public String relatedFoundRequest(Model model, @PathVariable String code) {
        LostResponseDto lostDto = lostService.getLostByCode(code);

        List<FoundResponseDto> relatedFoundList = foundService.getByMatchingCategoryAndDate(lostDto.lostDate() ,lostDto.categoryId());
        if (relatedFoundList.isEmpty())
            return "redirect:/found/";
        model.addAttribute("relatedFoundList", relatedFoundList);
        return "public/lost/relatedFoundRequest";
    }

    @GetMapping("/connect/{foundCode}")
    public String showLostConnectForm(@PathVariable String foundCode, Model model, CsrfToken csrfToken) {
        model.addAttribute("foundCode", foundCode);
        model.addAttribute("_csrf", csrfToken);
        return "public/lost/connectLost"; // your template
    }


    @PostMapping("/connect")
    public String connectLostWithFound(@RequestParam String foundCode, @RequestParam String lostCode, Model model) {
        LostFoundResponseDto response = lostAndFoundService.createLostFound(lostCode, foundCode);
        model.addAttribute("connectedItem", response);
        return "public/lost/connectSuccess";
    }


}
