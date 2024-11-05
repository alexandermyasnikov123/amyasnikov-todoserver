package net.dunice.todo.services;

import jakarta.transaction.Transactional;
import net.dunice.todo.DTOs.requests.ChangeStatusTodoRequest;
import net.dunice.todo.DTOs.requests.ChangeTextTodoRequest;
import net.dunice.todo.DTOs.requests.CreateTodoRequest;
import net.dunice.todo.DTOs.responses.GetNewsResponse;
import net.dunice.todo.entities.TodoEntity;

public interface TodosService {
    TodoEntity insertNewEntity(CreateTodoRequest request);

    GetNewsResponse findAllTodos(Boolean isReady, Integer page, Integer perPage);

    @Transactional
    void updateDetails(Long id, ChangeTextTodoRequest request);

    @Transactional
    void updateAllTodosStatus(ChangeStatusTodoRequest request);

    @Transactional
    void updateTodoStatus(Long id, ChangeStatusTodoRequest request);

    void deleteById(Long id);

    void deleteAllReady();
}
