package com.dhinesh.cache.exception;

import com.dhinesh.cache.common.APIResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(CacheException.class)
    public APIResponse<String> handleCacheException(CacheException cacheException) {
        return new APIResponse<>(cacheException.getHttpStatus().value(), cacheException.getMessage(),
                null);
    }
}
