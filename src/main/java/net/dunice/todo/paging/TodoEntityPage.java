package net.dunice.todo.paging;

import lombok.experimental.Delegate;
import net.dunice.todo.entities.TodoEntity;
import org.springframework.data.domain.Page;

public record TodoEntityPage(
        @Delegate
        Page<TodoEntity> page,
        long readyTodos,
        long notReadyTodos
) implements Page<TodoEntity> {
}

