package com.example.cool_kan.service;

import com.example.cool_kan.model.Board;
import com.example.cool_kan.model.Kolumn;
import com.example.cool_kan.repository.BoardRepository;
import com.example.cool_kan.repository.KolumnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KolumnService {

    @Autowired
    private KolumnRepository kolumnRepository;

    @Autowired
    private BoardRepository boardRepository;

    public List<Kolumn> getKolumnsByBoardId(Long boardId) {
        return kolumnRepository.findByBoardId(boardId);
    }

    public Kolumn createKolumn(Long boardId, Kolumn kolumn) {
        return boardRepository.findById(boardId).map(board -> {
            kolumn.setBoard(board);
            return kolumnRepository.save(kolumn);
        }).orElseThrow(() -> new RuntimeException("Board not found"));
    }

    public Kolumn updateKolumn(Long id, Kolumn kolumnDetails) {
        return kolumnRepository.findById(id).map(kolumn -> {
            kolumn.setName(kolumnDetails.getName());
            return kolumnRepository.save(kolumn);
        }).orElseThrow(() -> new RuntimeException("Column not found"));
    }

    public void deleteKolumn(Long id) {
        kolumnRepository.deleteById(id);
    }
}