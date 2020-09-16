package com.example.LoggingApplication;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalCommentRepository extends JpaRepository<JournalComment, Long> {
}
