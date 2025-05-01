package com.lostAndFound.lostAndFound.controller;

import com.lostAndFound.lostAndFound.service.FoundService;
import com.lostAndFound.lostAndFound.service.LostAndFoundService;
import com.lostAndFound.lostAndFound.service.LostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admins/connect")
public class LostFoundController {
    private final LostService lostService;
    private final FoundService foundService;
    private final LostAndFoundService lostAndFoundService;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("lostFoundList", lostAndFoundService.getAll());
        return "admins/lostFound/index";
    }
}
