/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Muhammad Uzair
 */


package ucf.assignments;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;

public class AppController implements Initializable {

    static AppModel appModel = new AppModel();

    @FXML
    private TableView<ToDoItem> tableView;
    @FXML
    private TableColumn<ToDoItem, String> colDescription;
    @FXML
    private TableColumn<ToDoItem, LocalDate> colDueDate;
    @FXML
    private TableColumn<ToDoItem, Boolean> colCompleted;

    @FXML
    private TextField txtNewDescription;
    @FXML
    private DatePicker dtNewDueDate;

    @FXML
    private CheckBox chkbxNewCompleted;

    @FXML
    private CheckBox chkbxShowCompleted;
    @FXML
    private CheckBox chkbxShowIncomplete;

    @FXML
    private boolean showCompleted = true;
    @FXML
    private boolean showIncomplete = true;


    @FXML
    public void refreshToDoItems() {

        ToDoList filteredItems = new ToDoList();           //New ToDoList filteredItems

        ToDoList fullList;
        fullList = appModel.getToDoList();

        if (showCompleted && showIncomplete) {           //if showCompleted && showIncomplete

            filteredItems = fullList;
        } else if (showCompleted) {
            filteredItems.setToDoItems(fullList.getCompletedToDoItems());
        } else if (showIncomplete) {
            filteredItems.setToDoItems(fullList.getIncompleteToDoItems());
        }

        txtNewDescription.setText("");
        dtNewDueDate.setValue(null);
        chkbxNewCompleted.setSelected(false);

        tableView.setItems(getItemsToDisplay(filteredItems));
        tableView.refresh();

    }

    public ObservableList<ToDoItem> getItemsToDisplay(ToDoList list) {
        //Create ObservableList items
        //addAll ToDoItems from list to the ObservableList items
        //return ObservableList items
        ObservableList<ToDoItem> items = FXCollections.observableArrayList();
        if (list.getToDoItems() != null) {
            items.addAll(list.getToDoItems());
        }
        return items;
    }

    public DateFormat dateFormat = DateFormat.getDateInstance();

    @FXML
    public void btnLoadClicked(ActionEvent actionEvent) throws IOException {
        //Parent parentLoadList = FXML Loader getResource("LoadFile.fxml")
        //Scene sceneLoadList = new Scene parentLoadList
        //Stage window = (Stage)actionEvent (Node)get Source -> get Scene -> get Window
        //window set scene sceneLoadList
        //show window

        Parent parentLoadList = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LoadFile.fxml")));
        Scene sceneLoadList = new Scene(parentLoadList);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(sceneLoadList);
        window.show();

    }

    @FXML
    public void btnDeleteToDoItemClicked() {
        //toDoList -> removeItem
        appModel.getToDoList().removeItem(tableView.getSelectionModel().getSelectedItem().desc.get());
        refreshToDoItems();
    }

    @FXML
    public void btnDeleteToDoItemAll() {
        //toDoList -> Clear Whole list
        appModel.getToDoList().getToDoItems().clear();
        refreshToDoItems();
    }


    @FXML
    public void btnNewItemClicked() {
        appModel.getToDoList().addItem(txtNewDescription.getText(), dtNewDueDate.getValue(), chkbxNewCompleted.isSelected());
        refreshToDoItems();
    }

    @FXML
    public void btnCreateNewListClicked(ActionEvent actionEvent) throws IOException {
        //show window of New list
        Parent parentNewList = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("NewFile.fxml")));
        Scene sceneNewList = new Scene(parentNewList);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(sceneNewList);
        window.show();
    }

    @FXML
    public void btnSaveListClicked() {
        //appModel -> saveList()
        try {
            AppModel.saveList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void chkbxShowCompletedClicked() {
        showCompleted = chkbxShowCompleted.isSelected();
        refreshToDoItems();
    }

    public void chkbxShowIncompleteClicked() {
        showIncomplete = chkbxShowIncomplete.isSelected();
        refreshToDoItems();
    }

        @Override
        public void initialize (URL url, ResourceBundle rb){
            colDescription.setCellValueFactory(new PropertyValueFactory<>("desc"));
            colDueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
            colCompleted.setCellValueFactory(param -> {
                ToDoItem item = param.getValue();

                SimpleBooleanProperty booleanProperty = new SimpleBooleanProperty(item.getComplete());

                booleanProperty.addListener((observable, oldValue, newValue) -> item.setComplete(newValue));

                return booleanProperty;
            });
            colCompleted.setCellFactory(t -> {
                CheckBoxTableCell<ToDoItem, Boolean> cell = new CheckBoxTableCell<>();
                cell.setAlignment(Pos.BASELINE_CENTER);
                return cell;
            });
            tableView.setEditable(true);
            refreshToDoItems();
        }
    }
