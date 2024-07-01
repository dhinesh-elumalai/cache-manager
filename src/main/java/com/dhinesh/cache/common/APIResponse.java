package com.dhinesh.cache.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class APIResponse<T> {

    private int statusCode;

    private String message;

    private T data;

    public APIResponse<T> successResponse(String message, T data){
        return new APIResponse<>(200, message, data);
    }

    public APIResponse(HttpStatus httpStatus, String message, T data) {
        this.data = data;
        this.statusCode = httpStatus.value();
        this.message = message;
    }


    public APIResponse<T> serverFailure(String message, T data){
        return new APIResponse<>(500, message, data);
    }


    public APIResponse<T> clientFailure(String message, T data){
        return new APIResponse<>(400, message, data);
    }
}
