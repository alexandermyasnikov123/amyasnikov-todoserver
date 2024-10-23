package net.dunice.todo.models.response;

import net.dunice.todo.models.TodoEntity;

public record GetPaginatedTodosResponse(
        Iterable<TodoEntity> content,
        long notReady,
        long ready,
        long numberOfElements
) {
}
