package ru.malik.todolist.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;



@Entity
public class ToDo {

    @Id
    @GeneratedValue
    private long id;

    @NotEmpty(message = "Title is not empty")
    private String title;

    @NotEmpty(message = "Date is not empty")
    private Date targetDate;

    @Size(min=2, max=100, message = "Name must be no less than 2 and no more than 100 ")
    private String username;

    private boolean isCompleted;

    public ToDo(String title, Date targetDate, String username) {
        this.title = title;
        this.targetDate = targetDate;
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(Date targetDate) {
        this.targetDate = targetDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public boolean getCompleted() {return isCompleted;}
    @Override
    public String toString() {
        return "ToDo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", targetDate=" + targetDate +
                ", username='" + username + '\'' +
                ", isCompleted=" + isCompleted +
                '}';
    }
}
