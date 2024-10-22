package net.dunice.todo.data;

import net.dunice.todo.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodosRepository extends JpaRepository<Todo, Long> {
    void deleteAllByIsReadyTrue();
}
