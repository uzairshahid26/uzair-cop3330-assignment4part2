/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Muhammad Uzair
 */

package ucf.assignments;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

class ToDoListTest {

    @Test
    void addItem_Test() {
        //New ToDoList testList
        //Run testList addItem("NewItem1", "07/05/2021", false)
        //New ToDoItem newItem1
        //newItem1 set desc = "NewItem1", dueDate = "07/05/2021", completed = false
        //AssertTrue(testList contains newItem1)
        ToDoList testList = new ToDoList();
        testList.addItem("NewItem1", LocalDate.of(2021, 7, 5), false);
        ToDoItem newItem = new ToDoItem("NewItem1", LocalDate.of(2021, 7, 5), false);
        assertTrue(testList.getToDoItems().contains(newItem));
    }


    @Test
    void removeItem() {
        //New ToDoList testList
        //Run testList addItem("NewItem1", "07/05/2021", false)
        //New ToDoItem newItem1
        //newItem1 set desc = "NewItem1", dueDate = "07/05/2021", completed = false
        //Run testList removeItem("NewItem1")
        //AssertFalse(testList contains newItem1)
        ToDoList testList = new ToDoList();
        testList.addItem("NewItem1", LocalDate.of(2021, 7, 5), false);
        ToDoItem newItem = new ToDoItem("NewItem1", LocalDate.of(2021, 7, 5), false);
        testList.removeItem(newItem.getDesc());
        assertFalse(testList.getToDoItems().contains(newItem));
    }
}

