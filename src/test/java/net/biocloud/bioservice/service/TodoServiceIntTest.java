package net.biocloud.bioservice.service;

import net.biocloud.bioservice.Application;
import net.biocloud.bioservice.domain.Todo;
import net.biocloud.bioservice.repository.TodoRepository;
import net.biocloud.bioservice.web.api.CreateTodoRequest;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Test class for TodoService.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
@Transactional
public class TodoServiceIntTest {

    @Inject
    private TodoRepository todoRepository;

    @Inject
    private TodoService todoService;

    @Test
    public void assertThatCreationSuccess() {
        CreateTodoRequest createTodoRequest = new CreateTodoRequest();
        createTodoRequest.setTask("Test");
        Todo todo = todoService.createTodo(createTodoRequest);
        Assertions.assertThat(todo).isNotNull();

        todoRepository.delete(todo);
    }
}
