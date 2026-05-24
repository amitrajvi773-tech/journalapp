package com.placementtraining.jornalApp.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
@Data
public class User {
    @Id
    private Integer id;
    @Column(unique=true)
    @NonNull
    private String username;
    @NonNull
    private String password;

    @OneToMany
    private List<JournalEntry> journalEntries=new ArrayList<>();
}
