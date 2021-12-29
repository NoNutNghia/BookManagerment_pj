package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BookManagement extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/Book.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 848, 481);
        primaryStage.setTitle("Book Store Management");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
