package com.placementtraining.jornalApp.repository;

import com.placementtraining.jornalApp.entity.JournalEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JournalEntryRepository
        extends JpaRepository<JournalEntry, Integer> {

}