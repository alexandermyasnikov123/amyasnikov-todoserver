package net.dunice.todo.DTOs.responses;

import net.dunice.todo.entities.TodoEntity;

public record GetNewsResponse(
        Iterable<TodoEntity> content,
        Long ready,
        Long notReady,
        Long numberOfElements
) {
}
