package com.dhinesh.cache.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@Getter
public class CacheException extends Exception{

    private HttpStatus httpStatus;

    public CacheException(HttpStatus httpStatus, String errorMessage){
        super(errorMessage);
        this.httpStatus=httpStatus;
    }
}
