package net.dunice.todo.dto.request;

import jakarta.validation.constraints.NotNull;
import net.dunice.todo.constants.ValidationConstants;
import net.dunice.todo.data.entities.TodoEntity;
import org.hibernate.validator.constraints.Length;

public record CreateTodoRequest(
        @NotNull(message = ValidationConstants.TODO_TEXT_NOT_NULL)
        @Length(
                min = TodoEntity.DETAILS_MIN_LENGTH,
                max = TodoEntity.DETAILS_MAX_LENGTH,
                message = ValidationConstants.TODO_TEXT_SIZE_NOT_VALID
        )
        String text
) {
}
