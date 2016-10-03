package net.biocloud.bioservice.repository;

import net.biocloud.bioservice.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Spring Data JPA repository for the Todo entity.
 */
public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findAllByCompletedIsFalse();

    @Override
    void delete(Todo todo);
}
