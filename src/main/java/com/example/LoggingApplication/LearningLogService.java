package com.example.LoggingApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Reader;
import java.sql.Clob;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class LearningLogService {

    @Autowired
    LogEntryRepository logEntryRepository;

    public List<RenderedLogEntry> createRenderedLogEntries() {
        List<LogEntry> entries = logEntryRepository.findAll();
        List<RenderedLogEntry> renderedEntries = new ArrayList<RenderedLogEntry>();
        for (LogEntry entry : entries) {
            try {
                Long id = entry.getId();
                LocalDate date = entry.getDate();
                String author = entry.getAuthor();
                String title = entry.getTitle();
                Clob content = entry.getContent();

                Reader r = content.getCharacterStream();
                StringBuffer buffer = new StringBuffer();
                int ch;
                while ((ch = r.read()) != -1) {
                    buffer.append("" + (char) ch);
                }

                renderedEntries.add(new RenderedLogEntry(id, date, author, title, buffer.toString()));

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        return renderedEntries;
    }

    public void deleteLogEntry(@RequestParam Long id) {
        logEntryRepository.delete(logEntryRepository.getOne(id));
    }

}
