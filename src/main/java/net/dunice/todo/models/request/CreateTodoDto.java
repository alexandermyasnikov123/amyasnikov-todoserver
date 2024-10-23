package net.dunice.todo.models.request;

import net.dunice.todo.models.TodoEntity;
import org.hibernate.validator.constraints.Length;

public record CreateTodoDto(
        @Length(min = TodoEntity.DETAILS_MIN_LENGTH, max = TodoEntity.DETAILS_MAX_LENGTH)
        String text
) {
}
