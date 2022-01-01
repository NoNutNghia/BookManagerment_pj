package service.impl;

import controller.request.book.SearchBookRequest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Book;
import service.BookService;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookServiceImpl implements BookService, Initializable {

    @FXML
    private Button btnDvd;
    @FXML
    private TextField txtInterest;
    @FXML
    private TableView<Book> tblBook;
    @FXML
    private TableColumn<Book, String> col_id;
    @FXML
    private TableColumn<Book, String> col_title;
    @FXML
    private TableColumn<Book, String> col_author;
    @FXML
    private TableColumn<Book, String> col_publisher;
    @FXML
    private TableColumn<Book, Integer> col_publicYear;
    @FXML
    private TableColumn<Book, Integer> col_importPrice;
    @FXML
    private TableColumn<Book, Integer> col_exportPrice;
    @FXML
    private TableColumn<Book, Integer> col_nbrPage;
    @FXML
    private TableColumn<Book, Integer> col_length;
    @FXML
    private TableColumn<Book, Integer> col_width;
    @FXML
    private TableColumn<Book, Integer> col_status;

    ObservableList<Book> oblist = FXCollections.observableArrayList();


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

    @Override
    public ObservableList<Book> findAll() {
        ObservableList<Book> bookList = FXCollections.observableArrayList();

        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmanager_db", "root", "");

            //Query
            String sql = "select * from book where status=1";
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
    public void deleteDvd(Integer id) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmanager_db", "root", "");

            //Query
            String sql = "delete from book where id=?";

            statement = connection.prepareCall(sql);

            statement.setInt(1, id);

            statement.execute();


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

    public void setStatus(Integer id) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmanager_db", "root", "");

            //Query
            String sql = "update book set status=0 where id=?";

            statement = connection.prepareCall(sql);

            statement.setInt(1, id);

            statement.execute();


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
    public ObservableList<Book> searchBook(SearchBookRequest searchBookRequest) {
        ObservableList<Book> bookList = FXCollections.observableArrayList();

        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmanager_db", "root", "");

            String title = searchBookRequest.getTitle();
            String author = searchBookRequest.getAuthor();
            Integer publicYear = searchBookRequest.getPublicYear();

            //Query
            String sql = "select * from book where title="+ "'" + title + "'" + " and " + "author=" + "'" + author + "'" + " and " + "publicYear=" + publicYear;
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

    private int interest() {
        int sumImportPrice = 0, sumExportPrice = 0;

        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmanager_db", "root", "");

            //Query
            String sql = "select importPrice, exportPrice from book ";
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                sumImportPrice += resultSet.getInt("importPrice");
                sumExportPrice += resultSet.getInt("exportPrice");
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
        return (sumExportPrice - sumImportPrice);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showBook();
    }

    private void showBook() {
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        col_author.setCellValueFactory(new PropertyValueFactory<>("author"));
        col_publisher.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        col_publicYear.setCellValueFactory(new PropertyValueFactory<>("publicYear"));
        col_importPrice.setCellValueFactory(new PropertyValueFactory<>("importPrice"));
        col_exportPrice.setCellValueFactory(new PropertyValueFactory<>("exportPrice"));
        col_nbrPage.setCellValueFactory(new PropertyValueFactory<>("numberOfPage"));
        col_length.setCellValueFactory(new PropertyValueFactory<>("length"));
        col_width.setCellValueFactory(new PropertyValueFactory<>("width"));
        col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        oblist = findAll();
        tblBook.setItems(oblist);

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
        showBook();
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

        ObservableList<Book> selectedItem = tblBook.getSelectionModel().getSelectedItems();
        txtTitle.setText(selectedItem.get(0).getTitle());
        txtAuthor.setText(selectedItem.get(0).getAuthor());
        txtPublisher.setText(selectedItem.get(0).getPublisher());
        txtPublicYear.setText(String.valueOf(selectedItem.get(0).getPublicYear()));
        txtImportPrice.setText(String.valueOf(selectedItem.get(0).getImportPrice()));
        txtExportPrice.setText(String.valueOf(selectedItem.get(0).getExportPrice()));
        txtNbrPage.setText(String.valueOf(selectedItem.get(0).getNumberOfPage()));
        txtLength.setText(String.valueOf(selectedItem.get(0).getLength()));
        txtWidth.setText(String.valueOf(selectedItem.get(0).getWidth()));

        showBook();
    }

    public void searchBook(ActionEvent actionEvent) {
        String title = txtTitle.getText();
        String author = txtAuthor.getText();
        Integer publicYear = Integer.valueOf(txtPublicYear.getText());

        SearchBookRequest searchBookRequest = new SearchBookRequest(title, author, publicYear);

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        col_author.setCellValueFactory(new PropertyValueFactory<>("author"));
        col_publisher.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        col_publicYear.setCellValueFactory(new PropertyValueFactory<>("publicYear"));
        col_importPrice.setCellValueFactory(new PropertyValueFactory<>("importPrice"));
        col_exportPrice.setCellValueFactory(new PropertyValueFactory<>("exportPrice"));
        col_nbrPage.setCellValueFactory(new PropertyValueFactory<>("numberOfPage"));
        col_length.setCellValueFactory(new PropertyValueFactory<>("length"));
        col_width.setCellValueFactory(new PropertyValueFactory<>("width"));
        col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        oblist = searchBook(searchBookRequest);
        tblBook.setItems(oblist);
    }

    public void deleteBook(ActionEvent actionEvent) {
        ObservableList<Book> selectedItem = tblBook.getSelectionModel().getSelectedItems();
        deleteDvd(selectedItem.get(0).getId());
        showBook();
    }

    public void sellBook(ActionEvent actionEvent) {
        ObservableList<Book> selectedItem = tblBook.getSelectionModel().getSelectedItems();
        setStatus(selectedItem.get(0).getId());
        showBook();
    }

    public void interestMoney(ActionEvent actionEvent) {
        txtInterest.setText(String.valueOf(interest()));
    }

    public void changeLayout(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/Dvd.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 848, 481);

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setTitle("DVD Management");
        window.setScene(scene);
        window.show();
    }
}

