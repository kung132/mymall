package com.nkp.myblog.dto.api;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Getter
@Slf4j
public class ApiResponse<T> {

    private final int code;

    private final String message;

    private final T data;

    @Builder
    public ApiResponse(HttpStatus status, String message, T data) {
        status = status == null ? HttpStatus.OK : status;
        this.code = status.value();
        this.message = message == null ? status.getReasonPhrase() : message;
        this.data = data;
    }
}