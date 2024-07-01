package com.dhinesh.cache.controller;

import com.dhinesh.cache.common.APIResponse;
import com.dhinesh.cache.dto.BookRequest;
import com.dhinesh.cache.dto.BookResponse;
import com.dhinesh.cache.exception.CacheException;
import com.dhinesh.cache.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BookController {

    @Autowired
    private IBookService bookService;

    /**
     * Cacheable annotation with value books, stores the books response in local and
     * use it whenever the same request is raised again (unless cleared)
     *
     * unless is used to apply condition on the result.
     * @return
     */
    @GetMapping(value = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
    @Cacheable(value = "books", unless="#result.getStatusCode()!=200")
    public APIResponse<List<BookResponse>> getAllBooks() throws CacheException {
        return new APIResponse<>(HttpStatus.OK, "Success", bookService.getAllBooks());
    }

    /**
     * Specifying the key in Cacheable annotation makes it cache the value by key and return
     * data if the same key is used again.
     * @param bookId
     * @return
     */
    @GetMapping(value = "/books/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Cacheable(value = "books", key = "#bookId", condition = "#bookId!=1")
    public APIResponse<BookResponse> getBookById(@PathVariable("id") Integer bookId) throws CacheException {
        return new APIResponse<>(HttpStatus.OK, "Success", bookService.getBookById(bookId));
    }

    /**
     *
     * @param bookId
     * @return
     */
    @DeleteMapping(value = "/books/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public APIResponse<BookResponse> deleteBookById(@PathVariable("id") Integer bookId) throws CacheException {
        return new APIResponse<>(HttpStatus.OK, "Success", bookService.deleteBookById(bookId));
    }

    /**
     *
     * @param bookRequest
     * @return
     */
    @PostMapping(value = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
    public APIResponse<BookResponse> createBook(@Validated @RequestBody BookRequest bookRequest){
        return new APIResponse<>(HttpStatus.CREATED, "Success", bookService.createBook(bookRequest));
    }

    /**
     *
     * @return
     */
    @PutMapping(value = "/clear-cache", produces = MediaType.APPLICATION_JSON_VALUE)
    @CacheEvict(allEntries = true, value = {"books"})
    public APIResponse<BookResponse> clearCache(){
        return new APIResponse<>(HttpStatus.OK, "Success", null);
    }
}
