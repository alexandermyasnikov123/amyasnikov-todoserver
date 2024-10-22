package net.dunice.todo.data;

import lombok.AllArgsConstructor;
import lombok.val;
import net.dunice.todo.models.Todo;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class TodosService {
    private final TodosRepository repository;

    public void toggleTodoCompleted(Todo todo) {
        val willBeCompleted = !todo.getIsReady();
        createOrUpdate(todo.withIsReady(willBeCompleted));
    }

    public void createNew(String details) {
        val now = new Date();
        val todo = Todo.builder()
                .creationDate(now)
                .lastUpdateDate(now)
                .details(details)
                .build();

        createOrUpdate(todo);
    }

    public void deleteAllReady() {
        repository.deleteAllByIsReadyTrue();
    }

    public void createOrUpdate(Todo todo) {
        repository.save(todo);
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
