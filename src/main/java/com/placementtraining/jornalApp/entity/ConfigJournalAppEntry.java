package com.placementtraining.jornalApp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class ConfigJournalAppEntry {
    private String key;
    private String value;
}
