package com.example.board.service;

import com.example.board.dto.BoardDeleteDto;
import com.example.board.dto.BoardDto;
import com.example.board.dto.BoardUpdateDto;
import com.example.board.entity.Board;
import com.example.board.entity.User;
import com.example.board.repository.BoardRepository;
import com.example.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;


    @Transactional(readOnly = true)
    public Page<Board> getBoards(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return boardRepository.findAll(pageable);
    }


    @Transactional
    public void create(BoardDto boardDto) {
        LocalDateTime now = LocalDateTime.now();
        final User user = userRepository.findByUsername(boardDto.getUsername())
                .orElseThrow(() -> new RuntimeException("일치하는 사용자가 없습니다."));

        Board board = Board.builder()
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .user(user)
                .createdAt(now)
                .build();

        boardRepository.save(board);
    }

    @Transactional
    public void update(BoardUpdateDto dto) {

        final User user = userRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new RuntimeException("일치하는 사용자가 없습니다."));

        final Board board = boardRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("일치하는 게시물이 없습니다."));

        if(user.getUsername().equals(dto.getUsername())) {
            board.update(dto.getTitle(), dto.getContent());
            boardRepository.save(board);
        }else {
            throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");
        }

    }

    @Transactional
    public void delete(BoardDeleteDto dto) {
        final User user = userRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new RuntimeException("일치하는 사용자가 없습니다."));

        final Board board = boardRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("일치하는 게시물이 없습니다."));

        if(user.getUsername().equals(dto.getUsername())) {
            boardRepository.delete(board);
        }else {
            throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");
        }
    }

}
