package com.placementtraining.jornalApp.entity;

import com.placementtraining.jornalApp.enums.Sentiment;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@NoArgsConstructor
@Data
public class JournalEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String content;
    private LocalDateTime date;
    private Sentiment sentiment;






}
