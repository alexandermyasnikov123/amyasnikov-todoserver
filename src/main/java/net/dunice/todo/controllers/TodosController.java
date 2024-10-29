package net.dunice.todo.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import net.dunice.todo.DTOs.requests.ChangeStatusTodoRequest;
import net.dunice.todo.DTOs.requests.ChangeTextTodoRequest;
import net.dunice.todo.DTOs.requests.CreateTodoRequest;
import net.dunice.todo.DTOs.responses.GetPaginatedResponse;
import net.dunice.todo.DTOs.responses.TodoEntityPageResponse;
import net.dunice.todo.DTOs.responses.TodoEntityResponse;
import net.dunice.todo.DTOs.responses.common.BaseSuccessResponse;
import net.dunice.todo.constants.ErrorCodes;
import net.dunice.todo.constants.ValidationConstants;
import net.dunice.todo.entities.TodoEntity;
import net.dunice.todo.paging.TodoEntityPage;
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
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping(path = "v1/todo")
@RequiredArgsConstructor
public class TodosController {
    private final TodosService service;

    @GetMapping
    public ResponseEntity<GetPaginatedResponse> findTodosByStatus(
            @NotNull(message = ValidationConstants.REQUIRED_INT_PARAM_PAGE_IS_NOT_PRESENT)
            Integer page,
            @NotNull(message = ValidationConstants.REQUIRED_INT_PARAM_PER_PAGE_IS_NOT_PRESENT)
            Integer perPage,
            Boolean status
    ) {
        TodoEntityPage result = service.findAllTodos(status, page, perPage);
        TodoEntityPageResponse response = new TodoEntityPageResponse(
                result.getContent(), result.readyTodos(), result.notReadyTodos(), result.getNumberOfElements()
        );
        GetPaginatedResponse body = new GetPaginatedResponse(ErrorCodes.OK, response);
        return ResponseEntity.ok(body);
    }

    @PostMapping
    public ResponseEntity<TodoEntityResponse> createTodo(
            @RequestBody
            @Valid
            CreateTodoRequest dto
    ) {
        TodoEntity data = service.createNew(dto.text());
        TodoEntityResponse body = new TodoEntityResponse(ErrorCodes.OK, data);
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
            ChangeStatusTodoRequest dto) {
        service.updateAllTodosStatus(dto.status());
        return ResponseEntity.ok(BaseSuccessResponse.success());
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<BaseSuccessResponse> deleteTodo(
            @PathVariable
            @PositiveOrZero(message = ValidationConstants.ID_MUST_BE_POSITIVE)
            Long id
    ) {
        service.deleteById(id);
        return ResponseEntity.ok(BaseSuccessResponse.success());
    }

    @PatchMapping(path = "status/{id}")
    public ResponseEntity<BaseSuccessResponse> changeStatus(
            @PathVariable
            @PositiveOrZero(message = ValidationConstants.ID_MUST_BE_POSITIVE)
            Long id,
            @RequestBody
            @Valid
            ChangeStatusTodoRequest dto) {
        service.updateTodoStatus(id, dto.status());
        return ResponseEntity.ok(BaseSuccessResponse.success());
    }

    @PatchMapping(path = "text/{id}")
    public ResponseEntity<BaseSuccessResponse> changeDetails(
            @PathVariable
            @PositiveOrZero(message = ValidationConstants.ID_MUST_BE_POSITIVE)
            Long id,
            @RequestBody
            @Valid
            ChangeTextTodoRequest dto) {
        service.updateDetails(id, dto.text());
        return ResponseEntity.ok(BaseSuccessResponse.success());
    }
}
