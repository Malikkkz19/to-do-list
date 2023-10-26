package ru.malik.todolist.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.malik.todolist.model.ToDo;

import java.util.List;

@Repository
public interface ToDoPagingRepository extends PagingAndSortingRepository<ToDo, Long> {
    List<ToDo> findAllByUsername(String username, Pageable pageable);

    List<ToDo> findAllByUsernameAndIsCompleted(String username, boolean isCompleted, Pageable pageable);

}
