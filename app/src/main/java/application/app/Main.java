package application.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // exceptions
        Parent root = FXMLLoader.load(getClass().getResource("Start window.fxml"));
        primaryStage.setTitle("A* algorithm");
        primaryStage.setScene(new Scene(root, 800, 550));
        primaryStage.show();
        //  linear-gradient(to bottom left, #52fff6, #9a35d4)
    }
}
