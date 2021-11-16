/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Muhammad Uzair
 */

package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LoadFile implements Initializable {

    public ArrayList<String> files = new ArrayList<>();

    @FXML
    private ListView<String> lvFilesItems;

    @FXML
    public void loadList(MouseEvent mouseEvent) throws IOException {
        //New String fileToLoad as
        //Return to todolist
        //Parent parentLoadList = FXML Loader getResource("app.fxml")
        //Scene sceneLoadList = new Scene parentLoadList
        //Stage window = (Stage)actionEvent (Node)get Source -> get Scene -> get Window
        //window set scene sceneLoadList
        //show window
        String fileToLoad = lvFilesItems.getSelectionModel().getSelectedItem();
        FXMLLoader loadApp = new FXMLLoader();
        loadApp.setLocation(getClass().getResource("app.fxml"));
        Parent parentLoadApp = loadApp.load();
        AppController appController = loadApp.getController();
        AppController.appModel.loadList(fileToLoad);
        appController.refreshToDoItems();
        Scene scene = new Scene(parentLoadApp);
        Stage window = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void loadFiles() {
        //New File savedLists (System.getenv("APPDATA") + File.separator + "ToDoListApp" + File.separator)
        //New File[] lists = savedLists listFiles
        //For all of lists array
        //If lists is a file
        //files.add(lists.getName())
        File savedLists = new File(System.getenv("APPDATA") + File.separator + "ToDoListApp" + File.separator);
        File[] lists = savedLists.listFiles();
        if (lists != null) {
            for (File l :
                    lists) {
                if (l.isFile()) {
                    files.add(l.getName().substring(0,l.getName().length()-4));
                }
            }
        }
    }

    public ObservableList<String> getObservableFileList(){
        //Create ObservableList returnList
        //addAll from ArrayList files to ObservableList returnList
        ObservableList<String> returnList = FXCollections.observableArrayList();
        returnList.addAll(files);
        return returnList;
    }


    public void initialize(URL url, ResourceBundle rb) {
        //loadFiles()
        //for each title in files
        //  add item to lvFilesItems
        loadFiles();
        lvFilesItems.setItems(getObservableFileList());
    }

}