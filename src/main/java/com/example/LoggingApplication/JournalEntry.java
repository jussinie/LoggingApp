package com.example.LoggingApplication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class JournalEntry extends AbstractPersistable<Long> {

    private LocalDate date;
    private String author;
    private String title;
    private String content;

}
