package com.example.LoggingApplication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.Lob;
import java.sql.Clob;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class LogEntry extends AbstractPersistable<Long> {

    private LocalDate date;
    private String author;
    private String title;
    @Lob
    private Clob content;

}
