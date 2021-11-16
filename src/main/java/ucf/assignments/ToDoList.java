/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Muhammad Uzair
 */

package ucf.assignments;

import java.time.LocalDate;
import java.util.*;

public class ToDoList {
    String title;
    Collection<ToDoItem> toDoItems;

    public ToDoList(){
        this("NewList",new ArrayList<>());
    }

    public ToDoList(String title) {
        this(title, new ArrayList<>());
    }

    public ToDoList(String title, Collection<ToDoItem> items) {
        this.title = title;
        this.toDoItems = items;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Collection<ToDoItem> getToDoItems() {
        return toDoItems;
    }

    public Collection<ToDoItem> getCompletedToDoItems() {
        Collection<ToDoItem> completedItems = new ArrayList<>();
        //for each ToDoItem in toDoList
        //  if toDoList.getCompleted is true
        //      add ToDoItem to completedItems
        if (!toDoItems.isEmpty()){
            for (ToDoItem i :
                    toDoItems) {
                if (i.getComplete()) {
                    completedItems.add(i);
                }
            }
        }
        return completedItems;
    }

    public Collection<ToDoItem> getIncompleteToDoItems() {
        Collection<ToDoItem> incompleteItems = new ArrayList<>();
        //for each ToDoItem in toDoList
        //  if toDoList.getCompleted is false
        //      add ToDoItem to completedItems
        if (!toDoItems.isEmpty()){
            for (ToDoItem i :
                    toDoItems)
                if (!i.getComplete()) {
                    incompleteItems.add(i);
                }
        }
        return incompleteItems;
    }

    public void setToDoItems(Collection<ToDoItem> toDoItems) {
        this.toDoItems = toDoItems;
    }

    public void addItem(String desc, LocalDate dueDate, boolean complete){
        //Create a new ToDoItem object
        //Add the new ToDoItem to the ToDoItem Collection;
        ToDoItem item = new ToDoItem(desc, dueDate, complete);
        this.toDoItems.add(item);
    }
    public void removeItem(String desc){
        //Collection removeIf item desc equals parameter desc
        if(!toDoItems.isEmpty()){
            toDoItems.removeIf(i -> desc.equals(i.getDesc()));
        }
    }
}