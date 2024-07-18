package com.example.cool_kan.repository;

import com.example.cool_kan.model.Kolumn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KolumnRepository extends JpaRepository<Kolumn, Long> {
    List<Kolumn> findByBoardId(Long boardId);
}