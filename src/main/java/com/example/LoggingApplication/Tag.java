package com.example.LoggingApplication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Tag extends AbstractPersistable<Long> {

    private String tag;
    @ManyToMany(mappedBy = "journalTags")
    private List<JournalEntry> journalEntries = new ArrayList<>();

}
