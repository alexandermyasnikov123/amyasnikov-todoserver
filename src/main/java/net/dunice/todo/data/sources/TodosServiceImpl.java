package net.dunice.todo.data.sources;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.val;
import net.dunice.todo.data.entities.TodoEntity;
import net.dunice.todo.others.TodoEntityPage;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodosServiceImpl implements TodosService {
    private final TodosRepository repository;

    @Override
    public TodoEntity createNew(String details) {
        val todo = TodoEntity.builder()
                .details(details)
                .id(0L)
                .isReady(false)
                .build();

        return repository.save(todo);
    }

    @Override
    public TodoEntityPage findAllTodos(Boolean isReady, Integer page, Integer perPage) {
        val request = PageRequest.of(page, perPage);

        val data = isReady == null ? repository.findAll(request) : repository.findAllByIsReady(isReady, request);

        val ready = data.stream().filter(TodoEntity::getIsReady).count();
        val notReady = data.getNumberOfElements() - ready;

        return new TodoEntityPage(data, ready, notReady);
    }

    @Transactional
    @Override
    public void updateDetails(long id, String details) {
        val todo = repository.findById(id).orElseThrow();
        todo.setDetails(details);
        repository.save(todo);
    }

    @Transactional
    @Override
    public void updateAllTodosStatus(boolean isReady) {
        val allTodos = repository.findAll();
        val modifiedTodos = allTodos.stream().map(todo -> todo.withIsReady(isReady)).toList();

        repository.saveAll(modifiedTodos);
    }

    @Transactional
    @Override
    public void updateTodoStatus(long id, boolean isReady) {
        val todo = repository.findById(id).orElseThrow();
        todo.setIsReady(isReady);
        repository.save(todo);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAllReady() {
        repository.deleteAllByIsReadyTrue();
    }
}
