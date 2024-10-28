package net.dunice.todo.dto.response;

import net.dunice.todo.data.entities.TodoEntity;

public record GetPaginatedTodosResponse(
        Iterable<TodoEntity> content,
        long notReady,
        long ready,
        long numberOfElements
) {
}
