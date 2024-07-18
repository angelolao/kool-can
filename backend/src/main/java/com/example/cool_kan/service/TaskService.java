package com.example.cool_kan.service;

import com.example.cool_kan.model.Task;
import com.example.cool_kan.repository.KolumnRepository;
import com.example.cool_kan.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private KolumnRepository kolumnRepository;

    public List<Task> getTasksByKolumnId(Long kolumnId) {
        return taskRepository.findByKolumnId(kolumnId);
    }

    public Task createTask(Long kolumnId, Task task) {
        return kolumnRepository.findById(kolumnId).map(kolumn -> {
            task.setKolumn(kolumn);
            return taskRepository.save(task);
        }).orElseThrow(() -> new RuntimeException("Kolumn not found"));
    }

    public Task updateTask(Long id, Task taskDetails) {
        return taskRepository.findById(id).map(task -> {
            task.setTitle(taskDetails.getTitle());
            task.setDescription(taskDetails.getDescription());
            return taskRepository.save(task);
        }).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}