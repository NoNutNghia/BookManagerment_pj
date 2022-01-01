package service.impl;

import controller.request.dvd.SearchDvdRequest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Dvd;
import service.DvdService;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DvdServiceImpl implements DvdService, Initializable {

    @FXML
    private TextField txtInterest;
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
    private TextField txtSize;

    @FXML
    private TextField txtDuration;

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
    private TableView<Dvd> tblDvd;

    @FXML
    private TableColumn<Dvd, String> col_id;
    @FXML
    private TableColumn<Dvd, String> col_title;
    @FXML
    private TableColumn<Dvd, String> col_author;
    @FXML
    private TableColumn<Dvd, String> col_publisher;
    @FXML
    private TableColumn<Dvd, Integer> col_publicYear;
    @FXML
    private TableColumn<Dvd, Integer> col_importPrice;
    @FXML
    private TableColumn<Dvd, Integer> col_exportPrice;
    @FXML
    private TableColumn<Dvd, Integer> col_size;
    @FXML
    private TableColumn<Dvd, Integer> col_duration;
    @FXML
    private TableColumn<Dvd, Integer> col_status;

    ObservableList<Dvd> oblist = FXCollections.observableArrayList();

    @Override
    public ObservableList<Dvd> findAll() {
        ObservableList<Dvd> dvdList = FXCollections.observableArrayList();

        Connection connection = null;
        Statement statement = null;
        try {
            //Lay tat ca dia
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmanager_db", "root", "");

            //Query
            String sql = "select * from dvd where status=1";
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()) {
                Dvd dvd = new Dvd(resultSet.getInt("id"),resultSet.getString("title"),
                        resultSet.getString("author"),resultSet.getInt("publicYear"),
                        resultSet.getString("publisher"),resultSet.getInt("importPrice"),
                        resultSet.getInt("exportPrice"),resultSet.getBoolean("status"),
                        resultSet.getInt("size"),resultSet.getInt("duration"));
                dvdList.add(dvd);
            }

        } catch(SQLException ex) {
            Logger.getLogger(DvdServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try{
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DvdServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DvdServiceImpl.class.getName()).log(Level.SEVERE, null, ex);

                }
            }
        }
        return dvdList;
    }

    @Override
    public void insertDvd(Dvd dvd) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            //Lay tat ca dia
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmanager_db", "root", "");

            //Query
            String sql = "insert into dvd(title, author, publicYear, publisher, importPrice, exportPrice, size, duration) values(?, ? ,?, ?, ? ,?, ?, ?)";

            statement = connection.prepareCall(sql);

            statement.setString(1, dvd.getTitle());
            statement.setString(2, dvd.getAuthor());
            statement.setInt(3, dvd.getPublicYear());
            statement.setString(4, dvd.getPublisher());
            statement.setInt(5, dvd.getImportPrice());
            statement.setInt(6, dvd.getExportPrice());
            statement.setInt(7, dvd.getSize());
            statement.setInt(8, dvd.getDuration());

            statement.execute();

            txtTitle.setText("");
            txtAuthor.setText("");
            txtPublisher.setText("");
            txtPublicYear.setText("");
            txtImportPrice.setText("");
            txtExportPrice.setText("");
            txtSize.setText("");
            txtDuration.setText("");

        } catch(SQLException ex) {
            Logger.getLogger(DvdServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try{
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DvdServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DvdServiceImpl.class.getName()).log(Level.SEVERE, null, ex);

                }
            }
        }
    }

    @Override
    public void updateDvd(Dvd dvd) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            //Lay tat ca dia
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmanager_db", "root", "");

            //Query
            String sql = "update dvd set title=?, author=?, publicYear=?, publisher=?, importPrice=?, exportPrice=?, size=?, duration=? where id=?";

            statement = connection.prepareCall(sql);

            statement.setString(1, dvd.getTitle());
            statement.setString(2, dvd.getAuthor());
            statement.setInt(3, dvd.getPublicYear());
            statement.setString(4, dvd.getPublisher());
            statement.setInt(5, dvd.getImportPrice());
            statement.setInt(6, dvd.getExportPrice());
            statement.setInt(7, dvd.getSize());
            statement.setInt(8, dvd.getDuration());
            statement.setInt(9, dvd.getId());


        } catch(SQLException ex) {
            Logger.getLogger(DvdServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try{
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DvdServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DvdServiceImpl.class.getName()).log(Level.SEVERE, null, ex);

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
            String sql = "update dvd set status=0 where id=?";

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
    public void deleteDvd(Integer id) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmanager_db", "root", "");

            //Query
            String sql = "delete from dvd  where id=?";

            statement = connection.prepareCall(sql);

            statement.setInt(1, id);

            statement.execute();


        } catch(SQLException ex) {
            Logger.getLogger(DvdServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try{
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DvdServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DvdServiceImpl.class.getName()).log(Level.SEVERE, null, ex);

                }
            }
        }
    }

    private int interest() {
        int sumImportPrice = 0, sumExportPrice = 0;

        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmanager_db", "root", "");

            //Query
            String sql = "select importPrice, exportPrice from dvd ";
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
    public ObservableList<Dvd> searchDvd(SearchDvdRequest searchDvdRequest) {
        ObservableList<Dvd> dvdList = FXCollections.observableArrayList();

        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmanager_db", "root", "");

            String title = searchDvdRequest.getTitle();
            String author = searchDvdRequest.getAuthor();
            Integer publicYear = searchDvdRequest.getPublicYear();

            //Query
            String sql = "select * from dvd where title="+ "'" + title + "'" + " and " + "author=" + "'" + author + "'" + " and " + "publicYear=" + publicYear;
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Dvd dvd = new Dvd(resultSet.getInt("id"), resultSet.getString("title"),
                        resultSet.getString("author"), resultSet.getInt("publicYear"),
                        resultSet.getString("publisher"), resultSet.getInt("importPrice"),
                        resultSet.getInt("exportPrice"), resultSet.getBoolean("status"),
                        resultSet.getInt("size"),resultSet.getInt("duration"));
                dvdList.add(dvd);
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
        return dvdList;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showDvd();
    }

    private void showDvd() {
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        col_author.setCellValueFactory(new PropertyValueFactory<>("author"));
        col_publisher.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        col_publicYear.setCellValueFactory(new PropertyValueFactory<>("publicYear"));
        col_importPrice.setCellValueFactory(new PropertyValueFactory<>("importPrice"));
        col_exportPrice.setCellValueFactory(new PropertyValueFactory<>("exportPrice"));
        col_size.setCellValueFactory(new PropertyValueFactory<>("size"));
        col_duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        oblist = findAll();
        tblDvd.setItems(oblist);

    }

    public void insertDvd(ActionEvent actionEvent) {
        String title = txtTitle.getText();
        String author = txtAuthor.getText();
        String publisher = txtPublisher.getText();
        Integer publicYear = Integer.valueOf(txtPublicYear.getText());
        Integer importPrice = Integer.valueOf(txtImportPrice.getText());
        Integer exportPrice = Integer.valueOf(txtExportPrice.getText());
        Integer size = Integer.valueOf(txtSize.getText());
        Integer duration = Integer.valueOf(txtDuration.getText());
        Dvd dvd = new Dvd(title, author, publicYear, publisher, importPrice, exportPrice, size, duration);
        insertDvd(dvd);

        showDvd();
    }

    public void updateDvd(ActionEvent actionEvent) {

        showDvd();
    }


    public void resetField(ActionEvent actionEvent) {
        txtTitle.setText("");
        txtAuthor.setText("");
        txtPublisher.setText("");
        txtPublicYear.setText("");
        txtImportPrice.setText("");
        txtExportPrice.setText("");
        txtSize.setText("");
        txtDuration.setText("");
    }

    public void searchDvd(ActionEvent actionEvent) {
        String title = txtTitle.getText();
        String author = txtAuthor.getText();
        Integer publicYear = Integer.valueOf(txtPublicYear.getText());

        SearchDvdRequest searchDvdRequest = new SearchDvdRequest(title, author, publicYear);

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        col_author.setCellValueFactory(new PropertyValueFactory<>("author"));
        col_publisher.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        col_publicYear.setCellValueFactory(new PropertyValueFactory<>("publicYear"));
        col_importPrice.setCellValueFactory(new PropertyValueFactory<>("importPrice"));
        col_exportPrice.setCellValueFactory(new PropertyValueFactory<>("exportPrice"));
        col_size.setCellValueFactory(new PropertyValueFactory<>("size"));
        col_duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        oblist = searchDvd(searchDvdRequest);
        tblDvd.setItems(oblist);

    }

    public void deleteDvd(ActionEvent actionEvent) {
        ObservableList<Dvd> selectedItem = tblDvd.getSelectionModel().getSelectedItems();
        deleteDvd(selectedItem.get(0).getId());
        showDvd();
    }

    public void sellDvd(ActionEvent actionEvent) {
        ObservableList<Dvd> selectedItem = tblDvd.getSelectionModel().getSelectedItems();
        setStatus(selectedItem.get(0).getId());
        showDvd();
    }

    public void interestMoney(ActionEvent actionEvent) {
        txtInterest.setText(String.valueOf(interest()));
    }

    public void changeLayout(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/Book.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 848, 481);

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setTitle("Book Management");
        window.setScene(scene);
        window.show();
    }
}
