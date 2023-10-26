package ru.malik.todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.malik.todolist.controller.CountResponse;
import ru.malik.todolist.controller.ToDoCreateRequest;
import ru.malik.todolist.controller.ToDoUpdateRequest;
import ru.malik.todolist.errorhandler.BadRequestException;
import ru.malik.todolist.errorhandler.InvalidPageException;
import ru.malik.todolist.errorhandler.ResourceNotFoundException;
import ru.malik.todolist.model.ToDo;
import ru.malik.todolist.repository.ToDoPagingRepository;
import ru.malik.todolist.repository.ToDoRepository;

import java.util.List;

@Service
public class ToDoService {
    @Autowired
    private ToDoRepository toDoRepository;

    @Autowired
    private ToDoPagingRepository toDoPagingRepository;

    public ToDo create(ToDoCreateRequest toDoCreateRequest, String username) {
        ToDo toDo = new ToDo(toDoCreateRequest.getTitle(), toDoCreateRequest.getTargetDate(), username);
        return toDoRepository.save(toDo);
    }

    public void deleteById(String username, long id) {
        ToDo toDo = toDoRepository.findByUsernameAndId(username, id);
        if (toDo == null) {
            throw new ResourceNotFoundException("ToDo not found!");
        }
        toDoRepository.deleteById(id);
    }

    public ToDo updateById(String username, ToDoUpdateRequest toDoUpdateRequest, long id) {
        ToDo toDo = toDoRepository.findByUsernameAndId(username, id);
        if (toDo == null) {
            throw new ResourceNotFoundException("ToDo not found!");
        }
        toDo.setTitle(toDoUpdateRequest.getTitle());
        toDo.setTargetDate(toDoUpdateRequest.getTargetDate());

        return toDoRepository.save(toDo);
    }

    public ToDo readById(long id, String username) {
        ToDo toDo = toDoRepository.findByUsernameAndId(username, id);
        if (toDo == null) {
            throw new ResourceNotFoundException("ToDo not found!");
        }
        return toDo;
    }

    public List<ToDo> readAll(String username) {
        return toDoRepository.findAllByUsername(username);
    }

    public List<ToDo> readAllByPageable(String username, String pageSize, String pageNumber) {
        int _pageNumber = pageNumberStringToInteger(pageNumber);
        int _pageSize = pageSizeStringToInteger(pageSize);

        Pageable pageable = PageRequest.of(_pageNumber, _pageSize, Sort.by(Sort.Direction.ASC, "targetDate"));
        return toDoPagingRepository.findAllByUsername(username, pageable);
    }

    public int pageNumberStringToInteger(String pageNumber) {
        int _pageNumber;
        try {
            _pageNumber = Integer.parseInt(pageNumber);
        } catch (Exception e) {
            throw new InvalidPageException("Invalid page number");
        }

        if (_pageNumber < 0) {
            throw new InvalidPageException("Invalid page number");
        }
        return _pageNumber;
    }

    public int pageSizeStringToInteger(String pageSize) {
        int _pageSize;

        try {
            _pageSize = Integer.parseInt(pageSize);
        } catch (Exception e) {
            throw new InvalidPageException("Invalid page size");
        }

        if (_pageSize < 0) {
            throw new InvalidPageException("Invalid page size");
        }
        return _pageSize;
    }

    public List<ToDo> readAllByIsCompleted(String username, String isCompleted) {
        boolean _isCompleted = isCompletedStringToBoolean(isCompleted);
        return toDoRepository.findAllByUsernameAndIsCompleted(username, _isCompleted);
    }

    public boolean isCompletedStringToBoolean(String isCompleted) {
        try {
            return Boolean.parseBoolean(isCompleted);
        } catch(Exception e) {
            throw new BadRequestException("Invalid isCompleted");
        }
    }

    public List<ToDo> findAllByIsCompletedPageable(String username, String isCompleted, String pageSize, String pageNumber) {
        boolean _isCompleted = isCompletedStringToBoolean(isCompleted);
        int _pageNumber = pageNumberStringToInteger(pageNumber);
        int _pageSize = pageSizeStringToInteger(pageSize);

        Pageable pageable = PageRequest.of(_pageNumber, _pageSize, Sort.by(Sort.Direction.ASC, "targetDate"));
        return toDoPagingRepository.findAllByUsernameAndIsCompleted(username, _isCompleted, pageable);
    }

    public ToDo markCompleteById(long id, String username) {
        ToDo toDo = toDoRepository.findByUsernameAndId(username, id);
        if (toDo == null) {
            throw new ResourceNotFoundException("ToDo not found!");
        }
        toDo.setCompleted(!toDo.getCompleted());
        return toDoRepository.save(toDo);
    }

    public CountResponse countAll(String username) {
        return new CountResponse(toDoRepository.countByUsername(username));
    }

    public CountResponse countAllByIsCompleted(String username, String isCompleted) {
        boolean _isCompleted = isCompletedStringToBoolean(isCompleted);
        return new CountResponse(toDoRepository.countByUsernameAndIsCompleted(username, _isCompleted));
    }

}
