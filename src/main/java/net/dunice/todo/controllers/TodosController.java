package net.dunice.todo.controllers;

import lombok.AllArgsConstructor;
import lombok.val;
import net.dunice.todo.data.TodosService;
import net.dunice.todo.models.TodoEntity;
import net.dunice.todo.models.request.CreateTodoDto;
import net.dunice.todo.models.response.CustomSuccessResponse;
import net.dunice.todo.models.response.GetPaginatedTodosResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "v1/todo")
@AllArgsConstructor
public class TodosController {
    private final TodosService service;

    @GetMapping
    CustomSuccessResponse<GetPaginatedTodosResponse> findTodosByStatus(
            int page,
            int perPage,
            boolean status
    ) {
        val result = service.findAllTodos(status, page, perPage);
        val response = new GetPaginatedTodosResponse(
                result.getContent(), result.notReadyTodos(), result.readyTodos(), result.getNumberOfElements()
        );
        return CustomSuccessResponse.success(response);
    }

    @PostMapping
    CustomSuccessResponse<TodoEntity> createTodo(@RequestBody CreateTodoDto dto) {
        val data = service.createNew(dto.text());
        return CustomSuccessResponse.success(data);
    }
}

