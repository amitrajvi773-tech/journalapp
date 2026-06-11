package com.placementtraining.jornalApp.repository;

import com.placementtraining.jornalApp.entity.ConfigJournalAppEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigJournalAppRepository extends JpaRepository<ConfigJournalAppEntry,String> {
}
