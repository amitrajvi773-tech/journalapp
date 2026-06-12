package com.placementtraining.jornalApp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class ConfigJournalAppEntry {
    @Id
    private String configkey;
    private String value;
}
