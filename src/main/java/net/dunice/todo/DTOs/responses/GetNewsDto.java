package net.dunice.todo.DTOs.responses;

import net.dunice.todo.entities.TodoEntity;

public record GetNewsDto(
        Iterable<TodoEntity> content,
        long ready,
        long notReady,
        long numberOfElements
) {
}
