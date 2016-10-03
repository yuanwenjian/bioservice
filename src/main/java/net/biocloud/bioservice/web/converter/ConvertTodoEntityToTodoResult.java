package net.biocloud.bioservice.web.converter;

import net.biocloud.bioservice.domain.Todo;
import net.biocloud.bioservice.web.api.TodoResult;

import java.util.function.Function;

public enum ConvertTodoEntityToTodoResult implements Function<Todo, TodoResult> {
    INSTANCE;

    @Override
    public TodoResult apply(Todo todo) {
        TodoResult todoResult = new TodoResult();
        todoResult.setId(todo.getId());
        todoResult.setTask(todo.getTask());
        todoResult.setCreateTime(todo.getCreateTime());
        todoResult.setCompleted(todo.getCompleted());
        todoResult.setCompleteTime(todo.getCompleteTime());
        return todoResult;
    }
}
