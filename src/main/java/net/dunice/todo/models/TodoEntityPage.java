package net.dunice.todo.models;

import lombok.experimental.Delegate;
import org.springframework.data.domain.Page;

public record TodoEntityPage(
        @Delegate Page<TodoEntity> page,
        long readyTodos,
        long notReadyTodos
) implements Page<TodoEntity> {
}
