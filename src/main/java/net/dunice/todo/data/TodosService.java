package net.dunice.todo.data;

import lombok.AllArgsConstructor;
import lombok.val;
import net.dunice.todo.models.TodoEntity;
import net.dunice.todo.models.TodoEntityPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class TodosService {
    private final TodosRepository repository;

    public void toggleTodoCompleted(TodoEntity todo) {
        val willBeCompleted = !todo.getIsReady();
        createOrUpdate(todo.withIsReady(willBeCompleted));
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
                .build();

        return createOrUpdate(todo);
    }

    public void deleteAllReady() {
        repository.deleteAllByIsReadyTrue();
    }

    public TodoEntity createOrUpdate(TodoEntity todoEntity) {
        return repository.save(todoEntity);
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
