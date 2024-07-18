package com.example.cool_kan.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String name;

    private String pictureUrl;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Board> boards = new ArrayList<>();

    // Constructors
    public User() {
    }

    public User(String email, String name, String pictureUrl) {
        this.email = email;
        this.name = name;
        this.pictureUrl = pictureUrl;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public List<Board> getBoards() {
        return boards;
    }

    public void setBoards(List<Board> boards) {
        this.boards = boards;
    }

    // Convenience methods for managing boards
    public void addBoard(Board board) {
        boards.add(board);
        board.setUser(this);
    }

    public void removeBoard(Board board) {
        boards.remove(board);
        board.setUser(null);
    }
}