package com.example.LoggingApplication;

import org.springframework.beans.factory.annotation.Autowired;
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
        model.addAttribute("entries", logEntryRepository.findAll());
        return "index";
    }

    @PostMapping("/")
    public String saveEntry(@RequestParam String author, @RequestParam String title, @RequestParam String content) {
        logEntryRepository.save(new LogEntry(LocalDate.now(), author, title, content));
        return "redirect:/";
    }

}

