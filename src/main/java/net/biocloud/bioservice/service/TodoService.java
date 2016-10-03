package net.biocloud.bioservice.service;

import net.biocloud.bioservice.domain.Todo;
import net.biocloud.bioservice.repository.TodoRepository;
import net.biocloud.bioservice.web.api.CreateTodoRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Service class for managing to do entity.
 */
@Service
@Transactional
public class TodoService {

    private static final Logger LOG = LoggerFactory.getLogger(TodoService.class);

    @Inject
    private TodoRepository todoRepository;

    public Todo createTodo(CreateTodoRequest createTodoRequest) {
        Todo todo = new Todo();
        todo.setTask(createTodoRequest.getTask());
        todo.setCreateTime(System.currentTimeMillis());
        todo.setCompleted(false);
        todo.setCompleteTime(0L);
        todoRepository.save(todo);

        LOG.debug("Created a todo entry: {}", createTodoRequest.getTask());
        return todo;
    }

    @Transactional(readOnly = true)
    public List<Todo> listAllTodoes() {
        return todoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Todo> listAllUncompletedTodoes() {
        return todoRepository.findAllByCompletedIsFalse();
    }

    public void deleteTodo(Long id) {
        todoRepository.delete(id);
    }
}
