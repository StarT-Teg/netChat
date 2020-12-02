package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public class Controller {

    @FXML
    private TextField inputField;

    @FXML
    private TextArea chatArea;

    private Network network;

    @FXML
    public void initialize (){

    }

    @FXML
    public void sendMessage(){

        String message = inputField.getText();

        if (message.length() != 0){
            // Записываем сообщение в поток для сервера
            try {
                network.getDataOutputStream().writeUTF(message);
                chatArea.appendText("Вы написали в " + java.time.LocalDateTime.now() + ":");
                chatArea.appendText("\n");
                chatArea.appendText(message);
                chatArea.appendText("\n");
            } catch (IOException e) {
                e.printStackTrace();

               String errorMessage = "Сообщение не было Отправлено!";
                Client.showErrorMessage(e.getMessage(), errorMessage);
            }

        }

        inputField.setText("");

    }

    public void getMessage (String message){
        chatArea.appendText("Сервер ответил в " + java.time.LocalDateTime.now() + ":");
        chatArea.appendText("\n");
        chatArea.appendText(message);
        chatArea.appendText("\n");
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    @FXML
    public void exit () throws IOException {
        network.disconnect();
        System.exit(0);
    }

}
