package com.example.LoggingApplication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.sql.Clob;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class JournalEntry extends AbstractPersistable<Long> {

    private LocalDate date;
    private String author;
    private String title;
    @Lob
    private Clob content;
    @OneToMany(mappedBy = "journalEntry")
    private List<JournalComment> journalComments = new ArrayList<>();

}
