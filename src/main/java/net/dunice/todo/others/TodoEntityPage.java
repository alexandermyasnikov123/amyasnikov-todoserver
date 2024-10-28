package net.dunice.todo.others;

import lombok.experimental.Delegate;
import net.dunice.todo.data.entities.TodoEntity;
import org.springframework.data.domain.Page;

public record TodoEntityPage(
        @Delegate Page<TodoEntity> page,
        long readyTodos,
        long notReadyTodos
) implements Page<TodoEntity> {
}
