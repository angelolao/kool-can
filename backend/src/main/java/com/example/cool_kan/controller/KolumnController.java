package com.example.cool_kan.controller;

import com.example.cool_kan.model.Kolumn;
import com.example.cool_kan.service.KolumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boards/{boardId}/kolumns")
public class KolumnController {

    @Autowired
    private KolumnService kolumnService;

    @GetMapping
    public ResponseEntity<List<Kolumn>> getKolumnsByBoardId(@PathVariable Long boardId) {
        return ResponseEntity.ok(kolumnService.getKolumnsByBoardId(boardId));
    }

    @PostMapping
    public ResponseEntity<Kolumn> createKolumn(@PathVariable Long boardId, @RequestBody Kolumn kolumn) {
        return ResponseEntity.ok(kolumnService.createKolumn(boardId, kolumn));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Kolumn> updateKolumn(@PathVariable Long id, @RequestBody Kolumn kolumnDetails) {
        return ResponseEntity.ok(kolumnService.updateKolumn(id, kolumnDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteKolumn(@PathVariable Long id) {
        kolumnService.deleteKolumn(id);  // Changed from columnService to kolumnService
        return ResponseEntity.ok().build();
    }
}