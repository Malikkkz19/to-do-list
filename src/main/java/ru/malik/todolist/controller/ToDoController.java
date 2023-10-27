package ru.malik.todolist.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.malik.todolist.errorhandler.CustomException;
import ru.malik.todolist.model.ToDo;
import ru.malik.todolist.service.ToDoService;

import java.security.Principal;
import java.util.List;

@Api(tags = "To-Do-App")
@RestController
@CrossOrigin(origins = "*", allowCredentials = "true")
@ApiResponses(value = {
        @ApiResponse(code = 400, message = "Bad request", response = CustomException.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = CustomException.class),
        @ApiResponse(code = 403, message = "Forbidden", response = CustomException.class),
        @ApiResponse(code = 404, message = "Not Found", response = CustomException.class)
}
)
public class ToDoController {

    @Autowired
    private ToDoService toDoService;

    @ResponseStatus(code = HttpStatus.CREATED)
    @RequestMapping(value = "/api/todo", method = RequestMethod.POST)
    public ResponseEntity<ToDo> create(@Valid @RequestBody ToDoCreateRequest toDoCreateRequest, Principal principal) {
        return new ResponseEntity<>(toDoService.create(toDoCreateRequest, principal.getName()), HttpStatus.CREATED);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/api/todo")
    public ResponseEntity<List<ToDo>> readAll(Principal principal, @RequestParam(required = false) String isCompleted) {
        if (isCompleted != null) {
            return new ResponseEntity<>(toDoService.readAllByIsCompleted(principal.getName(), isCompleted), HttpStatus.OK);
        }
        return new ResponseEntity<>(toDoService.readAll(principal.getName()), HttpStatus.OK);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/api/auth/count")
    public ResponseEntity<CountResponse> countAll(Principal principal, @RequestParam(required = false) String isCompleted) {
        if (isCompleted != null) {
            return new ResponseEntity<>(toDoService.countAllByIsCompleted(principal.getName(), isCompleted), HttpStatus.OK);
        }
        return new ResponseEntity<>(toDoService.countAll(principal.getName()), HttpStatus.OK);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/api/auth/{pageNumber}/{pageSize}")
    public ResponseEntity<List<ToDo>> readAllPageable(Principal principal, @PathVariable String pageNumber, @PathVariable String pageSize, @RequestParam(required = false) String isCompleted) {
        if (isCompleted != null) {
            return new ResponseEntity<>(toDoService)
        }
    }

}
