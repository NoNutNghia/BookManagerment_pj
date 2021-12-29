package service.impl;

import controller.request.dvd.SearchDvdRequest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import models.Dvd;
import service.DvdService;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DvdServiceImpl implements DvdService, Initializable {

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
    private Button btnBook;

    @FXML
    private Button btnDvd;

    @FXML
    private TableView tblDvd;

    @Override
    public List<Dvd> findAll() {
        List<Dvd> dvdList = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        try {
            //Lay tat ca dia
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmanager_db", "root", "");

            //Query
            String sql = "select * from dvd";
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

    @Override
    public void deleteDvd(Integer id) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            //Lay tat ca dia
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmanager_db", "root", "");

            //Query
            String sql = "delete from dvd  where id=?";

            statement = connection.prepareCall(sql);

            statement.setInt(1, id);


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
    public List<Dvd> searchDvd(SearchDvdRequest searchDvdRequest) {
        List<Dvd> dvdList = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        try {
            //Lay tat ca dia
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmanager_db", "root", "");

            String title = searchDvdRequest.getTitle();
            String author = searchDvdRequest.getAuthor();
            Integer publicYear = searchDvdRequest.getPublicYear();

            //Query
            String sql = "select * from book where title=" + title + "or" +"author=" + author + "or" + "publicYear=" + publicYear;
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
    }

    public void updateDvd(ActionEvent actionEvent) {
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
        searchDvd(searchDvdRequest);
    }

    public void deleteDvd(ActionEvent actionEvent) {
    }

}
