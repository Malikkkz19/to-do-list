package ru.malik.todolist.controller;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToDoUpdateRequest {
     @NotEmpty(message = "Title is required")
     private String title;

     @NotNull(message = "Date is required")
     private Date targetDate;

}
