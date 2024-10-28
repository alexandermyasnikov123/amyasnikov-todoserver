package net.dunice.todo.DTOs.responses;

import net.dunice.todo.entities.TodoEntity;

public record GetPaginatedTodosResponse(
        Iterable<TodoEntity> content,
        long notReady,
        long ready,
        long numberOfElements
) {
}
