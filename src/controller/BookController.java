package controller;

import models.Book;
import service.BookService;

import java.sql.SQLException;
import java.util.List;

public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    //Lay du lieu tu db
    public List<Book> findALl() throws SQLException {
        return bookService.findAll();
    }

    public void insert(Book book) {
        bookService.insertBook(book);
    }

    public void update(Book book) {
        bookService.updateBook(book);
    }

    public void delete(int id) {
        bookService.deleteBook(id);
    }
}
