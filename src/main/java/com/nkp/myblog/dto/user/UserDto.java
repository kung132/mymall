package com.nkp.myblog.dto.user;

import com.nkp.myblog.domain.User;
import com.nkp.myblog.util.BeanUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String email;

    private String userName;

    private String password;

    private String passwordConfirm;

    private Boolean emailVerify;

    public User toEntity() {
        PasswordEncoder passwordEncoder = BeanUtils.getBean(PasswordEncoder.class);
        return User.builder()
                .email(email)
                .userName(userName)
                .password(passwordEncoder.encode(password))
                .build();

    }
}
