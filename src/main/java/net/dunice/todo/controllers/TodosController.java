package net.dunice.todo.controllers;

import lombok.AllArgsConstructor;
import lombok.val;
import net.dunice.todo.ErrorCodes;
import net.dunice.todo.data.TodosService;
import net.dunice.todo.models.Todo;
import net.dunice.todo.models.request.CreateTodoDto;
import net.dunice.todo.models.response.common_response.CustomSuccessResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "v1/todo")
@AllArgsConstructor
public class TodosController {
    private final TodosService service;

    @PostMapping
    CustomSuccessResponse<Todo> createTodo(@RequestBody CreateTodoDto dto) {
        val data = service.createNew(dto.text());
        return CustomSuccessResponse.from(ErrorCodes.UNKNOWN, data);
    }
}

