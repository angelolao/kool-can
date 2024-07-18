package com.example.cool_kan.controller;

import com.example.cool_kan.model.Task;
import com.example.cool_kan.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kolumns/{kolumnId}/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>> getTasksByKolumnId(@PathVariable Long kolumnId) {
        return ResponseEntity.ok(taskService.getTasksByKolumnId(kolumnId));
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@PathVariable Long kolumnId, @RequestBody Task task) {
        return ResponseEntity.ok(taskService.createTask(kolumnId, task));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
        return ResponseEntity.ok(taskService.updateTask(id, taskDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok().build();
    }
}