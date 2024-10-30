package net.dunice.todo.services;

import jakarta.transaction.Transactional;
import net.dunice.todo.DTOs.requests.ChangeStatusTodoRequest;
import net.dunice.todo.DTOs.requests.ChangeTextTodoRequest;
import net.dunice.todo.DTOs.requests.CreateTodoRequest;
import net.dunice.todo.DTOs.responses.GetNewsDto;
import net.dunice.todo.DTOs.responses.common.CustomSuccessResponse;
import net.dunice.todo.entities.TodoEntity;

public interface TodosService {
    CustomSuccessResponse<TodoEntity> insertNewEntity(CreateTodoRequest request);

    GetNewsDto findAllTodos(Boolean isReady, Integer page, Integer perPage);

    @Transactional
    void updateDetails(long id, ChangeTextTodoRequest request);

    @Transactional
    void updateAllTodosStatus(ChangeStatusTodoRequest request);

    @Transactional
    void updateTodoStatus(long id, ChangeStatusTodoRequest request);

    void deleteById(long id);

    void deleteAllReady();
}
