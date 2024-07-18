package com.example.cool_kan.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 1000)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kolumn_id")
    private Kolumn kolumn;

    // Constructors
    public Task() {
    }

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Kolumn getKolumn() {
        return kolumn;
    }

    public void setKolumn(Kolumn kolumn) {
        this.kolumn = kolumn;
    }
}