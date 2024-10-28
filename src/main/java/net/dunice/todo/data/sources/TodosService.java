package net.dunice.todo.data.sources;

import jakarta.transaction.Transactional;
import net.dunice.todo.data.entities.TodoEntity;
import net.dunice.todo.others.TodoEntityPage;

public interface TodosService {
    TodoEntity createNew(String details);

    TodoEntityPage findAllTodos(Boolean isReady, Integer page, Integer perPage);

    @Transactional
    void updateDetails(long id, String details);

    @Transactional
    void updateAllTodosStatus(boolean isReady);

    @Transactional
    void updateTodoStatus(long id, boolean isReady);

    void deleteById(long id);

    void deleteAllReady();
}
