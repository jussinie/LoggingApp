package com.example.LoggingApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Clob;
import java.time.LocalDate;

@Controller
public class DefaultController {

    @Autowired
    LogEntryRepository logEntryRepository;

    @GetMapping("/")
    public String home(Model model) {
        Pageable pageable = PageRequest.of(0,3, Sort.by("date").descending());
        model.addAttribute("entries", logEntryRepository.findAll(pageable));
        return "index";
    }


}

