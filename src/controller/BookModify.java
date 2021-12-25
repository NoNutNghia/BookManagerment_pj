package controller;
import models.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookModify {
    //Lay du lieu tu db
    public static List<Book> findALl() throws SQLException {
        List<Book> bookList = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        try {
            //Lay tat ca sach
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmanager_db", "root", "");

            //Query
            String sql = "select * from book";
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()) {
                Book book = new Book(resultSet.getInt("id"),resultSet.getString("title"),
                                     resultSet.getString("author"),resultSet.getInt("public_year"),
                                     resultSet.getString("publisher"),resultSet.getInt("import_price"),
                                     resultSet.getInt("export_price"),resultSet.getBoolean("status"),
                                     resultSet.getInt("number_of_page"),resultSet.getInt("width"),
                                     resultSet.getInt("length"));
                bookList.add(book);
            }

        } catch(SQLException ex) {
            Logger.getLogger(BookModify.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try{
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BookModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BookModify.class.getName()).log(Level.SEVERE, null, ex);

                }
            }
        }
        return bookList;
    }

    public static void insert(Book book) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            //Lay tat ca sach
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmanager_db", "root", "");

            //Query
            String sql = "insert into book(title, author, public_year, publisher, import_price, export_price, number_of_page, width, length) values(?, ? ,?, ?, ? ,?, ?, ? ,?)";

            statement = connection.prepareCall(sql);

            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setInt(3, book.getPublicYear());
            statement.setString(4, book.getPublisher());
            statement.setInt(5, book.getImportPrice());
            statement.setInt(6, book.getExportPrice());
            statement.setInt(7, book.getNumberOfPage());
            statement.setInt(8, book.getWidth());
            statement.setInt(9, book.getLength());

        } catch(SQLException ex) {
            Logger.getLogger(BookModify.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try{
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BookModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BookModify.class.getName()).log(Level.SEVERE, null, ex);

                }
            }
        }
    }

    public static void update(Book book) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            //Lay tat ca sach
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmanager_db", "root", "");

            //Query
            String sql = "update book set title=?, author=?, public_year=?, publisher=?, import_price=?, export_price=?, number_of_page=?, width=?, length=? where id=?";

            statement = connection.prepareCall(sql);

            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setInt(3, book.getPublicYear());
            statement.setString(4, book.getPublisher());
            statement.setInt(5, book.getImportPrice());
            statement.setInt(6, book.getExportPrice());
            statement.setInt(7, book.getNumberOfPage());
            statement.setInt(8, book.getWidth());
            statement.setInt(9, book.getLength());
            statement.setInt((10), book.getId());


        } catch(SQLException ex) {
            Logger.getLogger(BookModify.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try{
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BookModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BookModify.class.getName()).log(Level.SEVERE, null, ex);

                }
            }
        }
    }

    public static void delete(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            //Lay tat ca sach
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmanager_db", "root", "");

            //Query
            String sql = "delete from book  where id=?";

            statement = connection.prepareCall(sql);

            statement.setInt(1, id);


        } catch(SQLException ex) {
            Logger.getLogger(BookModify.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try{
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BookModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BookModify.class.getName()).log(Level.SEVERE, null, ex);

                }
            }
        }
    }
}
