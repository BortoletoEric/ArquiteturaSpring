package io.github.bortoletoeric.arquiteturaspring.todo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<TodoEntity, Integer> {
    boolean existsByDescription(String description);
}
