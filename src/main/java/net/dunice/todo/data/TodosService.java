package net.dunice.todo.data;

import lombok.RequiredArgsConstructor;
import lombok.val;
import net.dunice.todo.models.TodoEntity;
import net.dunice.todo.models.TodoEntityPage;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class TodosService {
    private final TodosRepository repository;

    public void updateAllTodosStatus(boolean isReady) {
        repository.updateAllTodosStatus(isReady);
    }

    public void updateTodoStatus(long id, boolean isReady) {
        repository.updateTodoStatus(id, isReady);
    }

    public TodoEntityPage findAllTodos(boolean isReady, int page, int perPage) {
        val data = repository.findAllByIsReady(isReady, PageRequest.of(page, perPage));
        val ready = data.stream().filter(TodoEntity::getIsReady).count();
        val notReady = data.getNumberOfElements() - ready;

        return new TodoEntityPage(data, ready, notReady);
    }

    public TodoEntity createNew(String details) {
        val now = new Date();
        val todo = TodoEntity.builder()
                .creationDate(now)
                .lastUpdateDate(now)
                .details(details)
                .id(0L)
                .isReady(false)
                .build();

        return repository.save(todo);
    }

    public void deleteAllReady() {
        repository.deleteAllByIsReadyTrue();
    }

    public void updateDetails(long id, String details) {
        repository.updateTodoDetails(id, details);
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
