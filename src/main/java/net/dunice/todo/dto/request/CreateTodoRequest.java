package net.dunice.todo.dto.request;

import net.dunice.todo.data.entities.TodoEntity;
import org.hibernate.validator.constraints.Length;

public record CreateTodoRequest(
        @Length(min = TodoEntity.DETAILS_MIN_LENGTH, max = TodoEntity.DETAILS_MAX_LENGTH)
        String text
) {
}
