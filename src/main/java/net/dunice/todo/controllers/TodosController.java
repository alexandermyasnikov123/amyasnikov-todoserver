package net.dunice.todo.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import net.dunice.todo.DTOs.requests.ChangeStatusTodoRequest;
import net.dunice.todo.DTOs.requests.ChangeTextTodoRequest;
import net.dunice.todo.DTOs.requests.CreateTodoRequest;
import net.dunice.todo.DTOs.responses.GetPaginatedResponse;
import net.dunice.todo.DTOs.responses.TodoEntityResponse;
import net.dunice.todo.DTOs.responses.TodosPageResponse;
import net.dunice.todo.DTOs.responses.common.BaseSuccessResponse;
import net.dunice.todo.constants.ErrorCodes;
import net.dunice.todo.constants.ValidationConstants;
import net.dunice.todo.services.TodosService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping(path = "v1/todo")
@RequiredArgsConstructor
public class TodosController {
    private final TodosService service;

    @GetMapping
    public ResponseEntity<GetPaginatedResponse> findTodosByStatus(
            @RequestParam
            @Positive(message = ValidationConstants.PAGE_MUST_BE_AT_LEAST_1)
            Integer page,
            @RequestParam
            @Min(value = 1, message = ValidationConstants.PER_PAGE_MUST_BE_AT_LEAST_1)
            @Max(value = 100, message = ValidationConstants.PER_PAGE_MUST_BE_LESS_100)
            Integer perPage,
            Boolean status
    ) {
        TodosPageResponse result = service.findAllTodos(status, page - 1, perPage);
        GetPaginatedResponse body = new GetPaginatedResponse(ErrorCodes.OK, result);
        return ResponseEntity.ok(body);
    }

    @PostMapping
    public ResponseEntity<TodoEntityResponse> createTodo(
            @RequestBody
            @Valid
            CreateTodoRequest dto
    ) {
        TodoEntityResponse body = service.insertNewEntity(dto);
        return ResponseEntity.ok(body);
    }

    @DeleteMapping
    public ResponseEntity<BaseSuccessResponse> deleteAllReady() {
        service.deleteAllReady();
        return ResponseEntity.ok(BaseSuccessResponse.success());
    }

    @PatchMapping
    public ResponseEntity<BaseSuccessResponse> changeTodoStatus(
            @RequestBody
            @Valid
            ChangeStatusTodoRequest dto
    ) {
        service.updateAllTodosStatus(dto);
        return ResponseEntity.ok(BaseSuccessResponse.success());
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<BaseSuccessResponse> deleteTodo(
            @PathVariable
            @Positive(message = ValidationConstants.ID_MUST_BE_POSITIVE)
            Long id
    ) {
        service.deleteById(id);
        return ResponseEntity.ok(BaseSuccessResponse.success());
    }

    @PatchMapping(path = "status/{id}")
    public ResponseEntity<BaseSuccessResponse> changeStatus(
            @PathVariable
            @Positive(message = ValidationConstants.ID_MUST_BE_POSITIVE)
            Long id,
            @RequestBody
            @Valid
            ChangeStatusTodoRequest dto
    ) {
        service.updateTodoStatus(id, dto);
        return ResponseEntity.ok(BaseSuccessResponse.success());
    }

    @PatchMapping(path = "text/{id}")
    public ResponseEntity<BaseSuccessResponse> changeDetails(
            @PathVariable
            @Positive(message = ValidationConstants.ID_MUST_BE_POSITIVE)
            Long id,
            @RequestBody
            @Valid
            ChangeTextTodoRequest dto
    ) {
        service.updateDetails(id, dto);
        return ResponseEntity.ok(BaseSuccessResponse.success());
    }
}
