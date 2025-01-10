package com.example.board.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long no;

    @Column(nullable = false, unique = true)
    String identity;
    @Column(nullable = false)
    String password;
    @Column(nullable = false)
    String username;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    List<Board> board = new ArrayList<>();

    @Builder
    private User(String identity, String password, String username) {
        this.identity = identity;
        this.password = password;
        this.username = username;
    }

    public void update(String username) {
        this.username = username;
    }
}
