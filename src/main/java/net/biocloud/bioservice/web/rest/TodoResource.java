package net.biocloud.bioservice.web.rest;

import com.codahale.metrics.annotation.Timed;
import net.biocloud.bioservice.domain.Todo;
import net.biocloud.bioservice.service.TodoService;
import net.biocloud.bioservice.web.api.CreateTodoRequest;
import net.biocloud.bioservice.web.api.TodoResult;
import net.biocloud.bioservice.web.converter.ConvertTodoEntityToTodoResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/todo")
public class TodoResource {

    private static final Logger LOG = LoggerFactory.getLogger(TodoResource.class);

    @Inject
    private TodoService todoService;

    @RequestMapping(value = "",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<TodoResult> createTodo(@RequestBody CreateTodoRequest createTodoRequest) {
        LOG.debug("REST POST - create a new todo: {}", createTodoRequest.getTask());
        Todo todo = todoService.createTodo(createTodoRequest);
        return new ResponseEntity<TodoResult>(ConvertTodoEntityToTodoResult.INSTANCE.apply(todo), HttpStatus.CREATED);
    }

    @RequestMapping(value = "",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<TodoResult>> listTodoes() {
        LOG.debug("REST GET - list all todoes");
        List<TodoResult> todoResults = todoService.listAllTodoes().stream()
                        .map(ConvertTodoEntityToTodoResult.INSTANCE)
                        .collect(Collectors.toList());
        return new ResponseEntity<List<TodoResult>>(todoResults, HttpStatus.OK);
    }
}
