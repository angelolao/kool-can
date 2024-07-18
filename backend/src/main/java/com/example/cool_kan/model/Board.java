package com.example.cool_kan.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "boards")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Kolumn> kolumns = new ArrayList<>();

    // Constructors
    public Board() {
    }

    public Board(String name) {
        this.name = name;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Kolumn> getKolumns() {
        return kolumns;
    }

    public void setKolumns(List<Kolumn> kolumns) {
        this.kolumns = kolumns;
    }

    // Helper methods
    public void addKolumn(Kolumn kolumn) {
        kolumns.add(kolumn);
        kolumn.setBoard(this);
    }

    public void removeKolumn(Kolumn kolumn) {
        kolumns.remove(kolumn);
        if (kolumn != null) {
            kolumn.setBoard(null);
        }
    }

    // toString method for debugging and logging
    @Override
    public String toString() {
        return "Board{" + "id=" + id + ", name='" + name + '\'' + ", user=" + (user != null ? user.getId() : "null") + ", kolumns=" + kolumns.size() + '}';
    }

    // equals and hashCode methods for entity comparison
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Board)) {
            return false;
        }
        Board board = (Board) o;
        return getId() != null && getId().equals(board.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}



