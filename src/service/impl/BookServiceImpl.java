package service.impl;

import controller.request.book.SearchBookRequest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import models.Book;
import service.BookService;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookServiceImpl implements BookService, Initializable {

    @FXML
    private TextField txtTitle;

    @FXML
    private TextField txtAuthor;

    @FXML
    private TextField txtPublisher;

    @FXML
    private TextField txtPublicYear;

    @FXML
    private TextField txtImportPrice;

    @FXML
    private TextField txtExportPrice;

    @FXML
    private TextField txtNbrPage;

    @FXML
    private TextField txtWidth;

    @FXML
    private TextField txtLength;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnBook;

    @FXML
    private Button btnDvd;

    @FXML
    private TableView tblBook;

    @Override
    public List<Book> findAll() {
        List<Book> bookList = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmanager_db", "root", "");

            //Query
            String sql = "select * from book where status = 1";
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Book book = new Book(resultSet.getInt("id"), resultSet.getString("title"),
                        resultSet.getString("author"), resultSet.getInt("publicYear"),
                        resultSet.getString("publisher"), resultSet.getInt("importPrice"),
                        resultSet.getInt("exportPrice"), resultSet.getBoolean("status"),
                        resultSet.getInt("numberOfPage"), resultSet.getInt("width"),
                        resultSet.getInt("length"));
                bookList.add(book);
            }

//            for (Book bookEntity : bookList) {
//                bookEntity
//                        btnUpdate
//                        btnDelete
//            }
        } catch (SQLException ex) {
            Logger.getLogger(BookServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BookServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BookServiceImpl.class.getName()).log(Level.SEVERE, null, ex);

                }
            }
        }
        return bookList;
    }

    @Override
    public void insertBook(Book book) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmanager_db", "root", "");

            //Query
            String sql = "insert into book(title, author, publicYear, publisher, importPrice, exportPrice, numberOfPage, width, length) values(?, ? ,?, ?, ? ,?, ?, ? ,?)";

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

            statement.execute();

            txtTitle.setText("");
            txtAuthor.setText("");
            txtPublisher.setText("");
            txtPublicYear.setText("");
            txtImportPrice.setText("");
            txtExportPrice.setText("");
            txtNbrPage.setText("");
            txtLength.setText("");
            txtWidth.setText("");



        } catch (SQLException ex) {
            Logger.getLogger(BookServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BookServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BookServiceImpl.class.getName()).log(Level.SEVERE, null, ex);

                }
            }
        }
    }

    @Override
    public void updateBook(Book book) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmanager_db", "root", "");

            //Query
            String sql = "update book set title=?, author=?, publicYear=?, publisher=?, importPrice=?, exportPrice=?, numberOfPage=?, width=?, length=? where id=?";

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


        } catch (SQLException ex) {
            Logger.getLogger(BookServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BookServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BookServiceImpl.class.getName()).log(Level.SEVERE, null, ex);

                }
            }
        }
    }

    @Override
    public void deleteBook(Integer id) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmanager_db", "root", "");

            //Query
            String sql = "delete from book  where id=?";

            statement = connection.prepareCall(sql);

            statement.setInt(1, id);


        } catch (SQLException ex) {
            Logger.getLogger(BookServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BookServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BookServiceImpl.class.getName()).log(Level.SEVERE, null, ex);

                }
            }
        }
    }

    @Override
    public List<Book> searchBook(SearchBookRequest searchBookRequest) {
        List<Book> bookList = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmanager_db", "root", "");

            String title = searchBookRequest.getTitle();
            String author = searchBookRequest.getAuthor();
            Integer publicYear = searchBookRequest.getPublicYear();

            //Query
            String sql = "select * from book where title=" + title + "or" + "author=" + author + "or" + "publicYear=" + publicYear;
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Book book = new Book(resultSet.getInt("id"), resultSet.getString("title"),
                        resultSet.getString("author"), resultSet.getInt("publicYear"),
                        resultSet.getString("publisher"), resultSet.getInt("importPrice"),
                        resultSet.getInt("exportPrice"), resultSet.getBoolean("status"),
                        resultSet.getInt("numberOfPage"), resultSet.getInt("width"),
                        resultSet.getInt("length"));
                bookList.add(book);
            }

        } catch (SQLException ex) {
            Logger.getLogger(BookServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BookServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BookServiceImpl.class.getName()).log(Level.SEVERE, null, ex);

                }
            }
        }
        return bookList;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private void showBook() {

    }

    public void insertBookImpl(ActionEvent actionEvent) {
        String title = txtTitle.getText();
        String author = txtAuthor.getText();
        String publisher = txtPublisher.getText();
        Integer publicYear = Integer.valueOf(txtPublicYear.getText());
        Integer importPrice = Integer.valueOf(txtImportPrice.getText());
        Integer exportPrice = Integer.valueOf(txtExportPrice.getText());
        Integer nbrPage = Integer.valueOf(txtNbrPage.getText());
        Integer width = Integer.valueOf(txtWidth.getText());
        Integer length = Integer.valueOf(txtLength.getText());
        Book book = new Book(title, author, publisher, publicYear, importPrice, exportPrice, nbrPage, width, length);
        insertBook(book);
    }

    public void resetField(ActionEvent actionEvent) {
        txtTitle.setText("");
        txtAuthor.setText("");
        txtPublisher.setText("");
        txtPublicYear.setText("");
        txtImportPrice.setText("");
        txtExportPrice.setText("");
        txtNbrPage.setText("");
        txtLength.setText("");
        txtWidth.setText("");

    }

    public void updateBook(ActionEvent actionEvent) {


    }

    public void searchBook(ActionEvent actionEvent) {
        String title = txtTitle.getText();
        String author = txtAuthor.getText();
        Integer publicYear = Integer.valueOf(txtPublicYear.getText());

        SearchBookRequest searchBookRequest = new SearchBookRequest(title, author, publicYear);

        searchBook(searchBookRequest);
    }

    public void deleteBook(ActionEvent actionEvent) {
    }
}

