package net.dunice.todo.services.impls;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.dunice.todo.entities.TodoEntity;
import net.dunice.todo.paging.TodoEntityPage;
import net.dunice.todo.repositories.TodosRepository;
import net.dunice.todo.services.TodosService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodosServiceImpl implements TodosService {
    private final TodosRepository repository;

    @Override
    public TodoEntity createNew(String details) {
        TodoEntity todo = TodoEntity.builder()
                .details(details)
                .id(0L)
                .isReady(false)
                .build();

        return repository.save(todo);
    }

    @Override
    public TodoEntityPage findAllTodos(Boolean isReady, Integer page, Integer perPage) {
        Pageable request = PageRequest.of(page, perPage);

        Page<TodoEntity> data = isReady == null ?
                repository.findAll(request) :
                repository.findAllByIsReady(isReady, request);

        long ready = data.stream().filter(TodoEntity::getIsReady).count();
        long notReady = data.getNumberOfElements() - ready;

        return new TodoEntityPage(data, ready, notReady);
    }

    @Transactional
    @Override
    public void updateDetails(long id, String details) {
        TodoEntity todo = repository.findById(id).orElseThrow();
        todo.setDetails(details);
        repository.save(todo);
    }

    @Transactional
    @Override
    public void updateAllTodosStatus(boolean isReady) {
        List<TodoEntity> allTodos = repository.findAll();
        List<TodoEntity> modifiedTodos = allTodos.stream().map(todo -> todo.withIsReady(isReady)).toList();

        repository.saveAll(modifiedTodos);
    }

    @Transactional
    @Override
    public void updateTodoStatus(long id, boolean isReady) {
        TodoEntity todo = repository.findById(id).orElseThrow();
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
