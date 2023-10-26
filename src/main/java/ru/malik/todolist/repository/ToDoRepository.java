package ru.malik.todolist.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.malik.todolist.model.ToDo;

import java.util.List;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Long> {
    List<ToDo> findAllByUsername(String username);
    List<ToDo> findAllByUsernameAndIsCompleted(String username, boolean isCompleted);

    ToDo findByUsernameAndId(String username, Long id);

    Long countByUsername(String username);
    Long countByUsernameAndIsCompleted(String username, boolean isCompleted);

}
