package com.example.cool_kan.repository;

import com.example.cool_kan.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByKolumnId(Long kolumnId);
}