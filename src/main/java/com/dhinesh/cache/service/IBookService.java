package com.dhinesh.cache.service;

import com.dhinesh.cache.dto.BookRequest;
import com.dhinesh.cache.dto.BookResponse;
import com.dhinesh.cache.exception.CacheException;

import java.util.List;

public interface IBookService {
    List<BookResponse> getAllBooks() throws CacheException;
    BookResponse getBookById(int id) throws CacheException;
    BookResponse createBook(BookRequest book);
    BookResponse deleteBookById(int id) throws CacheException;
}
