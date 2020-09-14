package com.example.LoggingApplication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class RenderedLogEntry extends AbstractPersistable<Long> {

    private Long id;
    private LocalDate date;
    private String author;
    private String title;
    private String content;

}
