package com.example.LoggingApplication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class JournalComment extends AbstractPersistable<Long> {

    private Date date;
    private String journalEntryCommentContent;
    private String commenter;
    @ManyToOne
    private JournalEntry journalEntry;

}
