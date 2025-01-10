package com.example.board.controller;

import com.example.board.dto.*;
import com.example.board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/users/sign-up")
    public void singUp(@RequestBody SignUpDto dto) {
        userService.singUp(dto);
    }

    @PostMapping("/users/sign-in")
    public void signIn(@RequestBody SignInDto dto) {
        userService.signIn(dto);
    }

    @PutMapping("/users")
    public void update(@RequestBody UserUpdateDto dto){
        userService.update(dto);
    }

    @DeleteMapping("/users")
    public void delete(@RequestBody UserDeleteDto dto) {
        userService.delete(dto);
    }

    @GetMapping("/users/detail")
    public UserDto getUser(@RequestBody UserGetDto dto) {
        return userService.getUser(dto);
    }
}
