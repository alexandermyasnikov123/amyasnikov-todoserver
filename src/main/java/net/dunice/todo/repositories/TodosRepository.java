package net.dunice.todo.repositories;

import jakarta.transaction.Transactional;
import net.dunice.todo.entities.TodoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodosRepository extends JpaRepository<TodoEntity, Long> {
    @Transactional
    void deleteAllByIsReadyTrue();

    Page<TodoEntity> findAllByIsReady(Boolean isReady, Pageable pageable);
}
