package net.dunice.todo.data;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.val;
import net.dunice.todo.models.TodoEntity;
import net.dunice.todo.models.TodoEntityPage;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodosService {
    private final TodosRepository repository;

    public TodoEntity createNew(String details) {
        val todo = TodoEntity.builder()
                .details(details)
                .id(0L)
                .isReady(false)
                .build();

        return repository.save(todo);
    }

    public TodoEntityPage findAllTodos(Boolean isReady, Integer page, Integer perPage) {
        val request = PageRequest.of(page, perPage);

        val data = isReady == null ? repository.findAll(request) : repository.findAllByIsReady(isReady, request);

        val ready = data.stream().filter(TodoEntity::getIsReady).count();
        val notReady = data.getNumberOfElements() - ready;

        return new TodoEntityPage(data, ready, notReady);
    }

    @Transactional
    public void updateDetails(long id, String details) {
        val todo = repository.findById(id).orElseThrow();
        todo.setDetails(details);
        repository.save(todo);
    }

    @Transactional
    public void updateAllTodosStatus(boolean isReady) {
        val allTodos = repository.findAll();
        val modifiedTodos = allTodos.stream().map(todo -> todo.withIsReady(isReady)).toList();

        repository.saveAll(modifiedTodos);
    }

    @Transactional
    public void updateTodoStatus(long id, boolean isReady) {
        val todo = repository.findById(id).orElseThrow();
        todo.setIsReady(isReady);
        repository.save(todo);
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }

    public void deleteAllReady() {
        repository.deleteAllByIsReadyTrue();
    }
}
