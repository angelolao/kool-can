package com.example.cool_kan.controller;

import com.example.cool_kan.model.Board;
import com.example.cool_kan.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boards")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping
    public ResponseEntity<List<Board>> getUserBoards(@AuthenticationPrincipal OAuth2User principal) {
        Long userId = Long.parseLong(principal.getAttribute("sub"));
        return ResponseEntity.ok(boardService.getBoardsByUserId(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Board> getBoardById(@PathVariable Long id) {
        return boardService.getBoardById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Board> createBoard(@RequestBody Board board, @AuthenticationPrincipal OAuth2User principal) {
        Long userId = Long.parseLong(principal.getAttribute("sub"));
        return ResponseEntity.ok(boardService.createBoard(board, userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Board> updateBoard(@PathVariable Long id, @RequestBody Board boardDetails) {
        return ResponseEntity.ok(boardService.updateBoard(id, boardDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return ResponseEntity.ok().build();
    }
}