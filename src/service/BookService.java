package service;

import controller.request.book.SearchBookRequest;
import models.Book;

import java.util.List;

public interface BookService {
    List<Book> findAll();

    void insertBook(Book book);

    void updateBook(Book book);

    void deleteDvd(Integer id);

    List<Book> searchBook(SearchBookRequest searchBookRequest);

}
