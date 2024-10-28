package net.dunice.todo.controllers;

import lombok.RequiredArgsConstructor;
import lombok.val;
import net.dunice.todo.data.entities.TodoEntity;
import net.dunice.todo.data.sources.TodosService;
import net.dunice.todo.dto.request.ChangeStatusTodoRequest;
import net.dunice.todo.dto.request.ChangeTextTodoRequest;
import net.dunice.todo.dto.request.CreateTodoRequest;
import net.dunice.todo.dto.response.BaseSuccessResponse;
import net.dunice.todo.dto.response.CustomSuccessResponse;
import net.dunice.todo.dto.response.GetPaginatedTodosResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "v1/todo")
@RequiredArgsConstructor
public class TodosController {
    private final TodosService service;

    @GetMapping
    public CustomSuccessResponse<GetPaginatedTodosResponse> findTodosByStatus(
            Integer page,
            Integer perPage,
            Boolean status
    ) {
        val result = service.findAllTodos(status, page, perPage);
        val response = new GetPaginatedTodosResponse(
                result.getContent(), result.notReadyTodos(), result.readyTodos(), result.getNumberOfElements()
        );
        return CustomSuccessResponse.success(response);
    }

    @PostMapping
    public CustomSuccessResponse<TodoEntity> createTodo(@RequestBody CreateTodoRequest dto) {
        val data = service.createNew(dto.text());
        return CustomSuccessResponse.success(data);
    }

    @DeleteMapping
    public BaseSuccessResponse deleteAllReady() {
        service.deleteAllReady();
        return BaseSuccessResponse.unknown();
    }

    @PatchMapping
    public BaseSuccessResponse changeTodoStatus(@RequestBody ChangeStatusTodoRequest dto) {
        service.updateAllTodosStatus(dto.status());
        return BaseSuccessResponse.unknown();
    }

    @DeleteMapping(path = "{id}")
    public BaseSuccessResponse deleteTodo(@PathVariable long id) {
        service.deleteById(id);
        return BaseSuccessResponse.unknown();
    }

    @PatchMapping(path = "status/{id}")
    public BaseSuccessResponse changeStatus(@PathVariable long id, @RequestBody ChangeStatusTodoRequest dto) {
        service.updateTodoStatus(id, dto.status());
        return BaseSuccessResponse.unknown();
    }

    @PatchMapping(path = "text/{id}")
    public BaseSuccessResponse changeDetails(@PathVariable long id, @RequestBody ChangeTextTodoRequest dto) {
        service.updateDetails(id, dto.text());
        return BaseSuccessResponse.unknown();
    }
}

