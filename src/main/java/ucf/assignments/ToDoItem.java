/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Muhammad Uzair
 */

package ucf.assignments;

import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

public class ToDoItem {

    SimpleStringProperty desc;
    LocalDate dueDate;
    Boolean complete;

    public ToDoItem(String desc, LocalDate dueDate, boolean complete) {
        this.desc = new SimpleStringProperty(desc);
        this.dueDate = dueDate;
        this.complete = complete;
    }

    public ToDoItem(String desc) {
        this.desc = new SimpleStringProperty(desc);
        this.complete = false;
    }

    public String getDesc() {
        return desc.get();
    }

    public void setDesc(String desc) {
        this.desc = new SimpleStringProperty(desc);
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean getComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public boolean itemCompare(ToDoItem toCompare) {
        return this.desc.equals(toCompare.getDesc()) && this.dueDate.equals(toCompare.getDueDate()) && Boolean.compare(this.complete, toCompare.getComplete()) == 0;
    }
}