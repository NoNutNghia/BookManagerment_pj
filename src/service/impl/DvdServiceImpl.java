

// NGUYEN NGOC NGHIA
package service.impl;

import javafx.scene.control.*;
import models.Dvd;
import service.DvdService;
import controller.request.dvd.SearchDvdRequest;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DvdServiceImpl implements DvdService, Initializable {

    // Button and text field in file .fxml
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
    private Button btnEdit;

    // Table View to show product information
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

    // Get id for edit and update process
    private int id;

    // The list only-read oblist to show information product on table view
    ObservableList<Dvd> oblist = FXCollections.observableArrayList();

    // Find all product that is available to sell
    @Override
    public ObservableList<Dvd> findAll() {
        ObservableList<Dvd> dvdList = FXCollections.observableArrayList();

        Connection connection = null;
        Statement statement = null;
        try {
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

    // Input a product and add information into database
    @Override
    public void insertDvd(Dvd dvd) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
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

    // Set information again product that user want to change to database
    @Override
    public void updateDvd(Dvd dvd, int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
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
            statement.setInt(9, id);

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

    // After sell a product, status of that product set to false. It means the product is unavailable to sell
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

    // Delete a product that user want to do
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

    // Calculate and return how much money user get after sell amount of product
    private int interest() {
        int sumImportPrice = 0, sumExportPrice = 0;

        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmanager_db", "root", "");

            //Query
            String sql = "select importPrice, exportPrice from dvd where status = 0";
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

    // Return all product that have information the same with data user input on 3 text fields
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

    // Show all products can sell in table view
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showDvd();
    }

    // Setup and show information to table view
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

    // Take all data user input on text field and add data into database, show all newest products
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

    /* Take all data from all field, click the button update and data of product user want will be changed in database,
       table view will show all newest product */
    public void updateDvd(ActionEvent actionEvent) {
        String title = txtTitle.getText();
        String author = txtAuthor.getText();
        String publisher = txtPublisher.getText();
        Integer publicYear = Integer.valueOf(txtPublicYear.getText());
        Integer importPrice = Integer.valueOf(txtImportPrice.getText());
        Integer exportPrice = Integer.valueOf(txtExportPrice.getText());
        Integer size = Integer.valueOf(txtSize.getText());
        Integer duration = Integer.valueOf(txtDuration.getText());
        Dvd dvd = new Dvd(title, author, publicYear, publisher , importPrice, exportPrice, size, duration);
        updateDvd(dvd, id);
        resetField(actionEvent);
        showDvd();
    }

    // Set all field to blank field, or out action edit and update product
    public void resetField(ActionEvent actionEvent) {
        txtTitle.setText("");
        txtAuthor.setText("");
        txtPublisher.setText("");
        txtPublicYear.setText("");
        txtImportPrice.setText("");
        txtExportPrice.setText("");
        txtSize.setText("");
        txtDuration.setText("");

        btnUpdate.setDisable(true);
        btnEdit.setDisable(false);
    }

    /* User fill out 3 fields title, author and public year, click search and all
       product have the same data will show on the table view */
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

    // User click the product that want to delete, the product will be removed and show all newest product
    public void deleteDvd(ActionEvent actionEvent) {
        ObservableList<Dvd> selectedItem = tblDvd.getSelectionModel().getSelectedItems();
        Optional<ButtonType> result = alertConfirm(selectedItem.get(0).getTitle()).showAndWait();
        if(result.get() == ButtonType.OK) {
            deleteDvd(selectedItem.get(0).getId());
            alertStatus("Delete product").showAndWait();
        }
        showDvd();
    }

    /* User click the product that want to sell, the product will be set status to false,
       create and write to file data of product, and show all newest product */
    public void sellDvd(ActionEvent actionEvent) throws IOException {
        ObservableList<Dvd> selectedItem = tblDvd.getSelectionModel().getSelectedItems();
        Optional<String> result = purchaser().showAndWait();
        if (result.isPresent()) {
            try {
                File fileName = new File("D:\\bill\\Dvd\\billInfo-" + selectedItem.get(0).getId() + ".txt");
                if (fileName.createNewFile()) {
                    setStatus(selectedItem.get(0).getId());
                    String purchaser = result.get();
                    LocalDateTime time = LocalDateTime.now();
                    FileWriter fileWriter = new FileWriter(fileName);
                    fileWriter.write("Purchaser: " + purchaser + "\n");
                    fileWriter.write("Title: " + selectedItem.get(0).getTitle() + "\n");
                    fileWriter.write("Author: " + selectedItem.get(0).getAuthor() + "\n");
                    fileWriter.write("Publisher: " + selectedItem.get(0).getPublisher() + "\n");
                    fileWriter.write("Public Year: " + selectedItem.get(0).getPublicYear() + "\n");
                    fileWriter.write("Size: " + selectedItem.get(0).getSize() + " KB\n");
                    fileWriter.write("Duration: " + selectedItem.get(0).getDuration() + " (s)\n");
                    fileWriter.write("Price: " + selectedItem.get(0).getExportPrice() + "\n");
                    fileWriter.write("Time at: " + time + "\n");
                    fileWriter.write("==================================================================\n");
                    fileWriter.write("Thank you and have a good day!!!");
                    fileWriter.close();
                    alertStatus("Sell product");
                } else {
                    errorAlert("File already exists.");
                }

            } catch (IOException e) {
                errorAlert("An error occurred.");
                e.printStackTrace();
            }
            showDvd();
        }
    }

    // Click the button "Interest" and user will know how much money can get after sell some products
    public void interestMoney(ActionEvent actionEvent) {
        txtInterest.setText(String.valueOf(interest()));
    }

    /* Click the biggest button and the scene will be changed,
       the title of scene and text on button before user clicking means the frame user is using */
    public void changeLayout(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/Book.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 848, 481);

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setTitle("Book Management");
        window.setScene(scene);
        window.show();
    }

    // Take all data where user click on table view, and fill out all field corresponding
    public void editDvd(ActionEvent actionEvent) {
        ObservableList<Dvd> selectedItem = tblDvd.getSelectionModel().getSelectedItems();
        txtTitle.setText(selectedItem.get(0).getTitle());
        txtAuthor.setText(selectedItem.get(0).getAuthor());
        txtPublisher.setText(selectedItem.get(0).getPublisher());
        txtPublicYear.setText(String.valueOf(selectedItem.get(0).getPublicYear()));
        txtImportPrice.setText(String.valueOf(selectedItem.get(0).getImportPrice()));
        txtExportPrice.setText(String.valueOf(selectedItem.get(0).getExportPrice()));
        txtSize.setText(String.valueOf(selectedItem.get(0).getSize()));
        txtDuration.setText(String.valueOf(selectedItem.get(0).getDuration()));
        id = selectedItem.get(0).getId();

        btnUpdate.setDisable(false);
        btnEdit.setDisable(true);
    }

    public Alert alertStatus(String result) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(result + " successfully");
        return alert;
    }

    public Alert alertConfirm(String product) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Do you want to delete product " + product + " ?");
        return alert;
    }

    public TextInputDialog purchaser() {
        TextInputDialog textInputDialog = new TextInputDialog();
        textInputDialog.setHeaderText("Purchaser info");
        textInputDialog.setContentText("Enter name of purchaser: ");
        return textInputDialog;
    }

    public Alert errorAlert(String error) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setContentText(error);
        return errorAlert;
    }

}
