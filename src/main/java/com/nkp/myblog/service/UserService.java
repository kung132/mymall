package com.nkp.myblog.service;

import com.nkp.myblog.domain.User;
import com.nkp.myblog.dto.user.UserDto;
import com.nkp.myblog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    public Page<User> list(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public User create(UserDto userDto) {
        User user = userDto.toEntity();
        return userRepository.save(user);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public void update(Long id, UserDto userDto) {
        User user = userDto.toEntity();
        user.setId(id);
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(user.getPassword());
        user.setPasswordConfirm(user.getPassword());

        userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
