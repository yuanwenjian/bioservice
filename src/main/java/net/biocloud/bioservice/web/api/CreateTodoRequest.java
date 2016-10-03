package net.biocloud.bioservice.web.api;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateTodoRequest {

    @NotNull
    @Size(min = 1, max = 1024)
    private String task;

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
