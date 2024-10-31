package net.dunice.todo.services.impls;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.dunice.todo.DTOs.requests.ChangeStatusTodoRequest;
import net.dunice.todo.DTOs.requests.ChangeTextTodoRequest;
import net.dunice.todo.DTOs.requests.CreateTodoRequest;
import net.dunice.todo.DTOs.responses.GetNewsResponse;
import net.dunice.todo.DTOs.responses.common.CustomSuccessResponse;
import net.dunice.todo.constants.ErrorCodes;
import net.dunice.todo.entities.TodoEntity;
import net.dunice.todo.errors.EntityNotFoundException;
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
    public CustomSuccessResponse<TodoEntity> insertNewEntity(CreateTodoRequest request) {
        TodoEntity todo = TodoEntity.builder()
                .details(request.text())
                .id(0L)
                .isReady(false)
                .build();

        return new CustomSuccessResponse<>(ErrorCodes.OK, repository.save(todo));
    }

    @Override
    public GetNewsResponse findAllTodos(Boolean isReady, Integer page, Integer perPage) {
        Pageable request = PageRequest.of(page, perPage);

        Page<TodoEntity> entityPage = isReady == null ?
                repository.findAll(request) :
                repository.findAllByIsReady(isReady, request);

        Long numberOfElements = Long.valueOf(entityPage.getNumberOfElements());
        Long ready = entityPage.stream().filter(TodoEntity::getIsReady).count();
        Long notReady = numberOfElements - ready;

        return new GetNewsResponse(entityPage.getContent(), ready, notReady, numberOfElements);
    }

    @Transactional
    @Override
    public void updateDetails(Long id, ChangeTextTodoRequest request) {
        TodoEntity todo = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        todo.setDetails(request.text());

        repository.save(todo);
    }

    @Transactional
    @Override
    public void updateAllTodosStatus(ChangeStatusTodoRequest request) {
        List<TodoEntity> allTodos = repository.findAll();
        List<TodoEntity> modifiedTodos = allTodos.stream()
                .map(todo -> todo.withIsReady(request.status()))
                .toList();

        repository.saveAll(modifiedTodos);
    }

    @Transactional
    @Override
    public void updateTodoStatus(Long id, ChangeStatusTodoRequest request) {
        TodoEntity todo = repository.findById(id).orElseThrow();
        todo.setIsReady(request.status());

        repository.save(todo);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAllReady() {
        repository.deleteAllByIsReadyTrue();
    }
}
