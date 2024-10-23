package net.dunice.todo.data;

import jakarta.transaction.Transactional;
import net.dunice.todo.models.TodoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodosRepository extends JpaRepository<TodoEntity, Long> {
    @Transactional
    void deleteAllByIsReadyTrue();

    Page<TodoEntity> findAllByIsReady(boolean isReady, Pageable pageable);
}
