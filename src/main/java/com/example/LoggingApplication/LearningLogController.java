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

import java.io.Reader;
import java.sql.Clob;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        List<JournalEntry> entries = journalEntryRepository.findAll();
        List<RenderedJournalEntry> renderedEntries = new ArrayList<RenderedJournalEntry>();
        for (JournalEntry entry: entries) {
            try {
            LocalDate date = entry.getDate();
            String author = entry.getAuthor();
            String title = entry.getTitle();
            Clob content = entry.getContent();

            Reader r = content.getCharacterStream();
            StringBuffer buffer = new StringBuffer();
            int ch;
            while ((ch = r.read())!=-1) {
                buffer.append(""+(char)ch);
            }

            renderedEntries.add(new RenderedJournalEntry(date, author, title, buffer.toString()));

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        model.addAttribute("entries", renderedEntries);
        return "journal";
    }

    @PostMapping("/journal")
    public String saveJournalEntry(@RequestParam String author, @RequestParam String title, @RequestParam String content) {
        journalEntryRepository.save(new JournalEntry(LocalDate.now(), author, title, ClobProxy.generateProxy(content)));
        return "redirect:/journal";
    }

}


