package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class Main extends Application {

    private final String PATH_SAMPLE_XML = "sample.fxml";

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(PATH_SAMPLE_XML));
        primaryStage.setTitle("Сетевой чат (В ПРОЦЕССЕ РАЗРАБОТКИ)");
        primaryStage.setScene(new Scene(root));
        primaryStage.show(); // Видимость
    }



    public static void main(String[] args) {
        launch(args);
    }


}
