package com.placementtraining.jornalApp.entity;
import jakarta.validation.constraints.Email;



import com.fasterxml.jackson.annotation.JsonProperty;
import com.placementtraining.jornalApp.entity.JournalEntry;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.annotation.Documented;
import java.util.ArrayList;
import java.util.List;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Email
    private String email;

    private boolean sentimentAnalysis;



    @OneToMany(cascade = CascadeType.ALL)
    private List<JournalEntry> journalEntries = new ArrayList<>();
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> userrole=new ArrayList<>();


}