/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Muhammad Uzair
 */

package ucf.assignments;

 import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;
import static ucf.assignments.AppModel.toDoList;

class AppModelTest {

    @Test
    void loadList() {
        //New AppModel app
        //Set app ToDoList title to TEST
        //Save list
        //Set app ToDoList title to notTEST
        //Run loadList for TEST
        //Assert equals ToDoList title and "TEST"
        //New File file = new list filepath
        //Delete file
        AppModel app = new AppModel();
        app.getToDoList().setTitle("TEST");
        try {
            app.saveList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        app.getToDoList().setTitle("notTest");
        app.loadList("TEST");
        assertEquals(app.getToDoList().getTitle(), "TEST");
        String filepath = System.getenv("APPDATA") + File.separator + "ToDoListApp" + File.separator + "TEST.csv";
        File file = new File(filepath);
        file.delete();
    }

    @Test
    void chkForDir_Test() {
        //Create string path in APPDATA
        //Run checkForDir using string path
        //Create file with string path
        //AssertTrue file exists
        //Delete file
        String path = System.getenv("APPDATA") + File.separator + "ToDoListApp" + File.separator + "TESTFILE.csv";
        AppModel.checkForDir(path);
        File file = new File(path);
        assertTrue(file.exists());
        file.delete();
    }

    @Test
    void checkForFile_Test() {
        //Create string path in APPDATA
        //Create string for fileName
        //Create file with fileName
        //Run checkForFile with path and fileName parameters
        //Assert equals for the toDoList Title and the fileName parameter
        String path = System.getenv("APPDATA") + File.separator + "ToDoListApp" + File.separator + "TESTFILE.csv";
        String fileName = "TESTFILE";
        File file = new File(path);
        AppModel.checkForFile(path, fileName);
        assertEquals(toDoList.getTitle().toLowerCase(), fileName.toLowerCase());
        file.delete();
    }

    @Test
    void saveList_Test() {
        //New AppModel app
        //Set the toDoList title to TEST
        //Save list
        //New file with TEST path
        //AssertTrue(file.exists())
        //Delete file
        AppModel app = new AppModel();
        app.getToDoList().setTitle("TEST");
        try {
            app.saveList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String path = System.getenv("APPDATA") + File.separator + "ToDoListApp" + File.separator + "TEST.csv";
        File file = new File(path);
        assertTrue(file.exists());
        file.delete();
    }
}
