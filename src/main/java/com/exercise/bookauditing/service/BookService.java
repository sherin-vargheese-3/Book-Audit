package com.exercise.bookauditing.service;

import com.exercise.bookauditing.dtos.BookDTO;
import com.exercise.bookauditing.exception.BookNotFoundException;
import com.exercise.bookauditing.model.Book;
import com.exercise.bookauditing.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(this::convertToDto)
                .toList();
    }

    public BookDTO createBook(Book book) {
        Book savedBook = bookRepository.save(book);
        return convertToDto(savedBook);
    }

    public BookDTO getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));
        return convertToDto(book);
    }

    public BookDTO updateBook(Long id, Book updatedBook) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));

        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setIsbn(updatedBook.getIsbn());
        existingBook.setPrice(updatedBook.getPrice());

        Book savedBook = bookRepository.save(existingBook);
        return convertToDto(savedBook);
    }

    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }

    public BookDTO convertToDto(Book book){
        return BookDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .isbn(book.getIsbn())
                .price(book.getPrice())
                .createdBy(book.getCreatedBy())
                .createdDate(book.getCreatedDate())
                .lastModifiedBy(book.getLastModifiedBy())
                .lastModifiedDate(book.getLastModifiedDate())
                .build();
    }
}
