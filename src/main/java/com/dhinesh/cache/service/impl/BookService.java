package com.dhinesh.cache.service.impl;

import com.dhinesh.cache.dto.BookRequest;
import com.dhinesh.cache.dto.BookResponse;
import com.dhinesh.cache.entity.Book;
import com.dhinesh.cache.exception.CacheException;
import com.dhinesh.cache.repository.BookRepository;
import com.dhinesh.cache.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService implements IBookService {

    @Autowired
    private BookRepository bookRepository;

    /**
     * @return
     */
    @Override
    public List<BookResponse> getAllBooks() throws CacheException {
        this.delayRequest();
        List<Book> books = bookRepository.findAll();
        if (books.isEmpty()) {
            throw new CacheException(HttpStatus.NO_CONTENT, "Book List is Empty");
        }
        System.out.println("Book Responses from DB : " + books);
        List<BookResponse> bookResponses = books.stream().map(this::convertBookToResponse).collect(Collectors.toList());
        System.out.println("Converted Book Responses : " + bookResponses);
        return bookResponses;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public BookResponse getBookById(int id) throws CacheException {

        delayRequest();
        Optional<Book> book = bookRepository.findById(id);
        if (!book.isPresent()) {
            throw new CacheException(HttpStatus.NO_CONTENT, "Given Book id is invalid / Not Found in DB");
        }
        return this.convertBookToResponse(book.get());
    }

    /**
     * @param bookRequest
     * @return
     */
    @Override
    public BookResponse createBook(BookRequest bookRequest) {
        Book book = this.convertRequestToBook(bookRequest);
        Book savedBook = bookRepository.save(book);
        return this.convertBookToResponse(savedBook);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public BookResponse deleteBookById(int id) throws CacheException {
        Optional<Book> book = bookRepository.findById(id);
        if (!book.isPresent()) {
            throw new CacheException(HttpStatus.NO_CONTENT, "Given Book id is invalid / Not Found in DB");
        }
        bookRepository.deleteById(id);
        return this.convertBookToResponse(book.get());
    }

    /**
     *
     * @param book
     * @return
     */
    private BookResponse convertBookToResponse(Book book) {
        return new BookResponse(book.getId(), book.getTitle(), book.getAuthor(),
                book.getPrice());
    }

    /**
     *
     * @param book
     * @return
     */
    private Book convertRequestToBook(BookRequest book) {
        return new Book( book.getTitle(), book.getAuthor(),
                book.getPrice());
    }

    /**
     * This is to delay the response in local intentionally
     */
    private void delayRequest(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
