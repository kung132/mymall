package com.nkp.myblog.controller.user;

import com.nkp.myblog.domain.User;
import com.nkp.myblog.dto.api.ApiResponse;
import com.nkp.myblog.dto.user.UserDto;
import com.nkp.myblog.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/user")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    private ApiResponse<User> create(@RequestBody UserDto userDto) {
        User user = userService.create(userDto);
        return ApiResponse.<User>builder()
                .status(HttpStatus.OK)
                .data(user)
                .build();
    }

    @GetMapping("/list")
    public ApiResponse<Page<User>> list(Pageable pageable) {
        Page<User> user = userService.list(pageable);
        return ApiResponse.<Page<User>>builder()
                .status(HttpStatus.OK)
                .data(user)
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<User> findById(@PathVariable Long id) {
        User user = userService.findById(id).orElseThrow();
        return ApiResponse.<User>builder()
                .status(HttpStatus.OK)
                .data(user)
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<User> update(@PathVariable Long id,
                                    @RequestBody UserDto userDto) {
        userService.update(id, userDto);
        return ApiResponse.<User>builder()
                .status(HttpStatus.OK)
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<User> delete(@PathVariable Long id) {
        userService.delete(id);
        return ApiResponse.<User>builder()
                .status(HttpStatus.OK)
                .build();
    }
}
