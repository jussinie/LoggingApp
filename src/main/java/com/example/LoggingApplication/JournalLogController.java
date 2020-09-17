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
import java.util.ArrayList;
import java.util.Date;

@Controller
public class JournalLogController {

    @Autowired
    JournalEntryRepository journalEntryRepository;

    @Autowired
    JournalLogService journalLogService;

    @Autowired
    JournalCommentRepository journalCommentRepository;

    @Transactional
    @GetMapping("/journal")
    public String showJournalEntries(Model model) {
        model.addAttribute("entries", journalLogService.createRenderedJournalEntries());
        model.addAttribute("journalComments", journalCommentRepository.findAll());
        return "journal";
    }

    @PostMapping("/journal")
    public String saveJournalEntry(@RequestParam String author, @RequestParam String title, @RequestParam String content) {
        journalEntryRepository.save(new JournalEntry(LocalDate.now(), author, title, ClobProxy.generateProxy(content), new ArrayList<>()));
        return "redirect:/journal";
    }

    @GetMapping("/deleteJournalPost/{id}")
    public String deleteJournalPost(@PathVariable Long id) {
        journalLogService.deleteJournalEntry(id);
        return "redirect:/journal";
    }

    @PostMapping("/commentJournalPost")
    public String saveComment(@RequestParam String journalEntryCommentContent, @RequestParam String commenter, @RequestParam Long journalId) {
        journalCommentRepository.save(new JournalComment(new Date(), journalEntryCommentContent, commenter, journalEntryRepository.getOne(journalId)));
        return "redirect:/journal";
    }

    @GetMapping("/journalEntry/{id}")
    public String showJournalEntry(@PathVariable Long id, Model model) {
        model.addAttribute("entry", journalLogService.selectJournalEntryById(id));
        return "entry";
    }

}


