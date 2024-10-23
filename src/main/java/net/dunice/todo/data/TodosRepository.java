package net.dunice.todo.data;

import jakarta.transaction.Transactional;
import net.dunice.todo.models.TodoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TodosRepository extends JpaRepository<TodoEntity, Long> {
    @Transactional
    void deleteAllByIsReadyTrue();

    Page<TodoEntity> findAllByIsReady(boolean isReady, Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "update todo t set t.isReady = :isReady")
    void updateAllTodosStatus(boolean isReady);

    @Transactional
    @Modifying
    @Query(value = "update todo t set t.isReady = :isReady where t.id = :id")
    void updateTodoStatus(long id, boolean isReady);

    @Transactional
    @Modifying
    @Query(value = "update todo t set t.details = :details where t.id = :id")
    void updateTodoDetails(long id, String details);
}
