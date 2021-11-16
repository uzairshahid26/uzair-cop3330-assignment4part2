/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Muhammad Uzair
 */

package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class NewFile {

    @FXML
    private TextField txtNewListTitle;

    public void btnCreateListClicked(ActionEvent actionEvent) throws IOException {
        //New String listTitle as btnCreateList.getText()
        //New ToDoList newList
        //newList setTitle(listTitle)
        //setToDoList(newList)
        //Return to todolist
        //Parent parentLoadList = FXML Loader getResource("app.fxml")
        //Scene sceneLoadList = new Scene parentLoadList
        //Stage window = (Stage)actionEvent (Node)get Source -> get Scene -> get Window
        //window set scene sceneLoadList
        //show window
        String listTitle = txtNewListTitle.getText();
        ToDoList newList = new ToDoList(listTitle);
        FXMLLoader loadApp = new FXMLLoader();
        loadApp.setLocation(getClass().getResource("app.fxml"));
        Parent parentLoadApp = loadApp.load();
        AppController appController = loadApp.getController();
        appController.appModel.setToDoList(newList);
        appController.refreshToDoItems();
        Scene scene = new Scene(parentLoadApp);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}