package com.dauren.TestTask.service;

import com.dauren.TestTask.dto.UserDto;
import com.dauren.TestTask.mapper.UserMapper;
import com.dauren.TestTask.model.User;
import com.dauren.TestTask.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public ResponseEntity<UserDto> createUser(UserDto user) {
        User checkUser = userRepository.findByEmail(user.getEmail());
        System.out.println(user.getPassword());
        if (!Objects.isNull(checkUser)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

        User newUser = userMapper.toModel(user);
        userRepository.save(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.toDto(newUser));
    }
}
