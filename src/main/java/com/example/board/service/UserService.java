package com.example.board.service;

import com.example.board.dto.*;
import com.example.board.entity.User;
import com.example.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.Singular;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void singUp(SignUpDto dto) {
        final User user = User.builder()
                .identity(dto.getIdentity())
                .password(dto.getPassword())
                .username(dto.getUsername())
                .build();

        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public void signIn(SignInDto dto) {
        // 결과가 null 이면 Exception 발생시키겠다.
        userRepository.findByIdentityAndPassword(dto.getIdentity(), dto.getPassword())
                .orElseThrow(() -> new RuntimeException("일치하는 아이디, 비밀번호가 없습니다."));
    }

    @Transactional
    public void update(UserUpdateDto dto) {
        final User user = userRepository.findByIdentityAndPassword(dto.getIdentity(), dto.getPassword())
                .orElseThrow(() -> new RuntimeException("일치하는 아이디, 비밀번호가 없습니다."));

        user.update(dto.getUsername());
        userRepository.save(user);
    }

    @Transactional
    public void delete(UserDeleteDto dto) {
        final User user = userRepository.findByIdentityAndPassword(dto.getIdentity(), dto.getPassword())
                .orElseThrow(() -> new RuntimeException("일치하는 아이디, 비밀번호가 없습니다."));

        userRepository.delete(user);

        // 2번 방안
        // userRepository.deleteByIdentityAndPassword(dto.getIdentity(), dto.getPassword());
    }

    @Transactional(readOnly = true)
    public UserDto getUser(UserGetDto dto) {
        final User user = userRepository.findByIdentityAndPassword(dto.getIdentity(), dto.getPassword())
                .orElseThrow(() -> new RuntimeException("일치하는 아이디, 비밀번호가 없습니다."));

        return UserDto.builder()
                .identity(user.getIdentity())
                .username(user.getUsername())
                .build();
    }


}
