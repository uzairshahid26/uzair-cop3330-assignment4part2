/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Muhammad Uzair
 */

package ucf.assignments;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Scanner;

public class AppModel {
    static ToDoList toDoList = new ToDoList();

    public ToDoList getToDoList() {
        return toDoList;
    }

    public void setToDoList(ToDoList toDoList) {
        this.toDoList = toDoList;
    }

    public void loadList(String fileName) {
        //Try
        //String fullPath = System.getProperty("user.home") + File.separator + "ToDoListApp" + File.separator + fileName + ".csv"
        //New Scanner file = Scanner(fullPath)
        //New ToDoList loadedList
        //setTitle loadedList to fileName
        //While file has next
        //New String data = file next
        //New String[] lineValues = data.split(",")
        //loadedList.addItem(lineValues[0], lineValues[1], lineValues[2])
        //setToDoList(loadedList)
        //Catch

        String path = System.getenv("APPDATA") + File.separator + "ToDoListApp" + File.separator;
        String fullPath = path + fileName + ".csv";

        checkForDir(path);
        checkForFile(fullPath, fileName);

        Scanner file = new Scanner(fullPath);

        ToDoList loadedList = new ToDoList(fileName);

        while(file.hasNextLine()) {
            String data = file.nextLine();
            String[] lineValues = data.split(",");
            if (lineValues.length >= 3) {
                String desc = lineValues[0].substring(1, lineValues[0].length() - 1);
                LocalDate dueDate = LocalDate.parse(lineValues[0].substring(1, lineValues[1].length() - 1));
                Boolean complete = Boolean.parseBoolean(lineValues[2].substring(1, lineValues[2].length() - 1));
                loadedList.addItem(desc, dueDate, complete);
            }
        }

        toDoList.setTitle(loadedList.getTitle());
        toDoList.setToDoItems(loadedList.getToDoItems());
    }

    public static void checkForDir(String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdir();
        }
    }

    public static void checkForFile(String fullPath, String fileName) {
        File file = new File(fullPath);
        if (!file.exists()) {
            ToDoList missingList = new ToDoList(fileName);
            toDoList = missingList;
            try {
                saveList();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void saveList() throws IOException {
        String fileName = System.getenv("APPDATA") + File.separator + "ToDoListApp" + File.separator + toDoList.getTitle() + ".csv";
        File listToSave = new File(fileName);
        Files.deleteIfExists(Path.of(listToSave.getPath()));
        listToSave.createNewFile();
        try {
            FileWriter fileWriter = new FileWriter(listToSave);
            if (!toDoList.toDoItems.isEmpty()){
                for (ToDoItem e :
                        toDoList.getToDoItems()) {
                    StringBuilder s = new StringBuilder("\"" + e.getDesc() + "\"" + "," + "\"" + String.valueOf(e.getDueDate()) + "\"" + "," + "\"" + String.valueOf(e.getComplete()) + "\"" + "\n");
                    fileWriter.write(String.valueOf(s));
                }
            }
            fileWriter.close();
        } catch (IOException e) {
        }
    }

}