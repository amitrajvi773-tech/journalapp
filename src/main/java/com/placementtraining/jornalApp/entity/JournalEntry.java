package com.placementtraining.jornalApp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name = "journal_enteries")

@Data
public class JournalEntry {
    @Id
    private Integer id;
    private String name;
    private String content;




}
