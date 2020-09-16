package com.example.LoggingApplication;

import org.hibernate.engine.jdbc.ClobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class JournalLogController {

    @Autowired
    JournalEntryRepository journalEntryRepository;

    @Autowired
    JournalLogService journalLogService;

    @Transactional
    @GetMapping("/journal")
    public String showJournalEntries(Model model) {
        model.addAttribute("entries", journalLogService.createRenderedJournalEntries());
        return "journal";
    }

    @PostMapping("/journal")
    public String saveJournalEntry(@RequestParam String author, @RequestParam String title, @RequestParam String content) {
        journalEntryRepository.save(new JournalEntry(LocalDate.now(), author, title, ClobProxy.generateProxy(content)));
        return "redirect:/journal";
    }

    @GetMapping("/deleteJournalPost/{id}/delete")
    public String deleteJournalPost(@PathVariable Long id) {
        journalLogService.deleteJournalEntry(id);
        return "redirect:/journal";
    }

    @GetMapping("/journalEntry/{id}")
    public String showJournalPost(@PathVariable Long id, Model model) {
        model.addAttribute("entry", journalLogService.selectJournalEntryById(id));
        return "entry";
    }

}


