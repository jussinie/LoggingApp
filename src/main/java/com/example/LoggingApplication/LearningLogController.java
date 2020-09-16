package com.example.LoggingApplication;

import org.hibernate.engine.jdbc.ClobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    LearningLogService learningLogService;

    @Transactional
    @GetMapping("/learningLog")
    public String showLearningLogEntries(Model model) {
        model.addAttribute("entries", learningLogService.createRenderedLogEntries());
        return "learningLog";
    }

    @PostMapping("/learningLog")
    public String saveLogEntry(@RequestParam String author, @RequestParam String title, @RequestParam String content) {
        logEntryRepository.save(new LogEntry(LocalDate.now(), author, title, ClobProxy.generateProxy(content)));
        return "redirect:/learningLog";
    }

    @GetMapping("/deleteLogPost/{id}/delete")
    public String deleteLogPost(@PathVariable Long id) {
        learningLogService.deleteLogEntry(id);
        return "redirect:/learningLog";
    }
}


