package juno.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import juno.Juno;

public class Main extends Application {

    private Juno juno = new Juno("./data/juno.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            String css = getClass().getResource("/view/style.css").toExternalForm();
            scene.getStylesheets().add(css);

            fxmlLoader.<MainWindow>getController().setJuno(juno);  // inject the Juno instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
