package controller;

import controller.request.book.SearchBookRequest;
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

    public void update(Book book, int id) {
        bookService.updateBook(book, id);
    }

    public void delete(int id) {
        bookService.deleteDvd(id);
    }

    public List<Book> search(SearchBookRequest searchBookRequest) {
        return bookService.searchBook(searchBookRequest);
    }

}
