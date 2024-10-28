package net.dunice.todo.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import net.dunice.todo.DTOs.requests.ChangeStatusTodoRequest;
import net.dunice.todo.DTOs.requests.ChangeTextTodoRequest;
import net.dunice.todo.DTOs.requests.CreateTodoRequest;
import net.dunice.todo.DTOs.responses.GetPaginatedTodosResponse;
import net.dunice.todo.DTOs.responses.common.BaseSuccessResponse;
import net.dunice.todo.DTOs.responses.common.CustomSuccessResponse;
import net.dunice.todo.constants.ValidationConstants;
import net.dunice.todo.entities.TodoEntity;
import net.dunice.todo.paging.TodoEntityPage;
import net.dunice.todo.services.TodosService;
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
            @NotNull(message = ValidationConstants.REQUIRED_INT_PARAM_PAGE_IS_NOT_PRESENT)
            Integer page,
            @NotNull(message = ValidationConstants.REQUIRED_INT_PARAM_PER_PAGE_IS_NOT_PRESENT)
            Integer perPage,
            Boolean status
    ) {
        TodoEntityPage result = service.findAllTodos(status, page, perPage);
        GetPaginatedTodosResponse response = new GetPaginatedTodosResponse(
                result.getContent(), result.notReadyTodos(), result.readyTodos(), result.getNumberOfElements()
        );
        return CustomSuccessResponse.success(response);
    }

    @PostMapping
    public CustomSuccessResponse<TodoEntity> createTodo(
            @RequestBody
            @Valid
            CreateTodoRequest dto) {
        TodoEntity data = service.createNew(dto.text());
        return CustomSuccessResponse.success(data);
    }

    @DeleteMapping
    public BaseSuccessResponse deleteAllReady() {
        service.deleteAllReady();
        return BaseSuccessResponse.success();
    }

    @PatchMapping
    public BaseSuccessResponse changeTodoStatus(
            @RequestBody
            @Valid
            ChangeStatusTodoRequest dto) {
        service.updateAllTodosStatus(dto.status());
        return BaseSuccessResponse.success();
    }

    @DeleteMapping(path = "{id}")
    public BaseSuccessResponse deleteTodo(
            @PathVariable
            @Positive(message = ValidationConstants.ID_MUST_BE_POSITIVE)
            Long id
    ) {
        service.deleteById(id);
        return BaseSuccessResponse.success();
    }

    @PatchMapping(path = "status/{id}")
    public BaseSuccessResponse changeStatus(
            @PathVariable
            @Positive(message = ValidationConstants.ID_MUST_BE_POSITIVE)
            Long id,
            @RequestBody
            @Valid
            ChangeStatusTodoRequest dto) {
        service.updateTodoStatus(id, dto.status());
        return BaseSuccessResponse.success();
    }

    @PatchMapping(path = "text/{id}")
    public BaseSuccessResponse changeDetails(
            @PathVariable
            @Positive(message = ValidationConstants.ID_MUST_BE_POSITIVE)
            Long id,
            @RequestBody
            @Valid
            ChangeTextTodoRequest dto) {
        service.updateDetails(id, dto.text());
        return BaseSuccessResponse.success();
    }
}

