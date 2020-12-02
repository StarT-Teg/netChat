package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class Client extends Application {

    private final String PATH_SAMPLE_XML = "view.fxml";

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Client.class.getResource(PATH_SAMPLE_XML));

        Parent root = loader.load();

        primaryStage.setTitle("Сетевой чат (В ПРОЦЕССЕ РАЗРАБОТКИ)");
        primaryStage.setScene(new Scene(root));
        primaryStage.show(); // Видимость

        // Создаём подключение
        Network network = new Network();

        if (!network.connect()){
            showErrorMessage("", "Server connection error!");
        }

        // Определяем, какой контроллер соответсвует форме, с которой работаем
        Controller controller = loader.getController();
        controller.setNetwork(network);

        network.waitMessage(controller);

/*        primaryStage.setOnCloseRequest(windowEvent -> {
            try {
                network.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });*/

    }


    public static void showErrorMessage(String message, String errorMessage) {
        Alert alert = new Alert (Alert.AlertType.ERROR);
        alert.setTitle("Проблемы с соединением...");
        alert.setHeaderText(errorMessage);
        alert.setContentText(message);
        // Ожидаем закрытия окна, чтобы продолжить
        alert.showAndWait();
    }




    public static void main(String[] args) {
        launch(args);
    }


}
