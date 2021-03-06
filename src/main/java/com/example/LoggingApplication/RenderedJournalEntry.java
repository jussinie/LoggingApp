package com.example.LoggingApplication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.Lob;
import java.sql.Clob;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class RenderedJournalEntry extends AbstractPersistable<Long> {

    private Long id;
    private LocalDate date;
    private String author;
    private String title;
    private String content;

}
