package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private TextField inputField;

    @FXML
    private ListView <String> listView;

    ObservableList <String> messageList = FXCollections
            .observableArrayList();

    @FXML
    public void initialize (){

        // FXML не работает с обычными массивами
        // Для добавления массива необходимо использовать оболочку ObservableList

        listView.setItems(messageList);

    }

    @FXML
    public void addWordToList (){
        // По умолчанию мы не можем просто так добавить элемент в спписок
        // До этого необходимо получить список всех элементов

        String word = inputField.getText();

        if (word.length() != 0){
            listView.getItems().add(word);
        }

        inputField.setText("");

    }

    @FXML
    public void exit (){
        System.exit(0);
    }

}
