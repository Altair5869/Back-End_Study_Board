package com.example.board.controller;

import com.example.board.dto.BoardDeleteDto;
import com.example.board.dto.BoardDto;
import com.example.board.dto.BoardUpdateDto;
import com.example.board.dto.UserDto;
import com.example.board.entity.Board;
import com.example.board.entity.User;
import com.example.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/boards/main")
    public Page<Board> boardLists(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  Model model){
        Page<Board> boardPage = boardService.getBoards(page, size);
        return boardPage;
    }

    @PostMapping("/boards/create")
    public void create(@RequestBody BoardDto dto) {
        boardService.create(dto);
    }

    @PutMapping("/boards")
    public void update(@RequestBody BoardUpdateDto dto) { boardService.update(dto); }

    @DeleteMapping("/boards")
    public void delete(@RequestBody BoardDeleteDto dto) { boardService.delete(dto); }
}
