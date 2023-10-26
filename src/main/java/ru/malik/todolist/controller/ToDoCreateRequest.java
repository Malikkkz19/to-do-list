package ru.malik.todolist.controller;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.malik.todolist.service.ToDoService;

import java.util.Date;

@Data
@AllArgsConstructor
public class ToDoCreateRequest {

    @NotEmpty(message = "Title is required")
    private String title;

    @NotNull(message = "Date is required")
    private Date targetDate;

    protected ToDoCreateRequest() {}

}
