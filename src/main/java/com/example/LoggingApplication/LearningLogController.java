package com.example.LoggingApplication;

import org.hibernate.engine.jdbc.ClobProxy;
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
public class LearningLogController {

    @Autowired
    LogEntryRepository logEntryRepository;

    @Autowired
    JournalEntryRepository journalEntryRepository;

    @GetMapping("/learningLog")
    public String showLearningLogEntries(Model model) {
        model.addAttribute("entries", logEntryRepository.findAll());
        return "learningLog";
    }

    @PostMapping("/learningLog")
    public String saveLogEntry(@RequestParam String author, @RequestParam String title, @RequestParam String content) {
        logEntryRepository.save(new LogEntry(LocalDate.now(), author, title, ClobProxy.generateProxy(content)));
        return "redirect:/";
    }

    @GetMapping("/journal")
    public String showJournalEntries(Model model) {
        model.addAttribute("entries", journalEntryRepository.findAll());
        return "journal";
    }

    @PostMapping("/journal")
    public String saveJournalEntry(@RequestParam String author, @RequestParam String title, @RequestParam String content) {
        journalEntryRepository.save(new JournalEntry(LocalDate.now(), author, title, ClobProxy.generateProxy(content)));
        return "redirect:/journal";
    }

}


