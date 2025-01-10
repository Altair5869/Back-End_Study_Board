package com.example.board.dto;

import lombok.Getter;

@Getter
public class BoardUpdateDto {
    String title;
    String content;
    String username;
    Long id;
}
