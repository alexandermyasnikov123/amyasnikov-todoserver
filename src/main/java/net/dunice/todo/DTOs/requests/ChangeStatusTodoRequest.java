package net.dunice.todo.DTOs.requests;

import jakarta.validation.constraints.NotNull;
import net.dunice.todo.constants.ValidationConstants;

public record ChangeStatusTodoRequest(
        @NotNull(message = ValidationConstants.TODO_STATUS_NOT_NULL)
        Boolean status
) {
}
