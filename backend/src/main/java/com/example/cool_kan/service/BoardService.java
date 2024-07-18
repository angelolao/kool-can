package com.example.cool_kan.service;

import com.example.cool_kan.model.Board;
import com.example.cool_kan.repository.BoardRepository;
import com.example.cool_kan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Board> getBoardsByUserId(Long userId) {
        return boardRepository.findByUserId(userId);
    }

    public Optional<Board> getBoardById(Long id) {
        return boardRepository.findById(id);
    }

    public Board createBoard(Board board, Long userId) {
        return userRepository.findById(userId).map(user -> {
            board.setUser(user);
            return boardRepository.save(board);
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Board updateBoard(Long id, Board boardDetails) {
        return boardRepository.findById(id).map(board -> {
            board.setName(boardDetails.getName());
            return boardRepository.save(board);
        }).orElseThrow(() -> new RuntimeException("Board not found"));
    }

    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }
}