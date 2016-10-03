package net.biocloud.bioservice.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "T_TODO")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Todo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "task", length = 1024)
    private String task;

    @NotNull
    @Column(name = "create_time", nullable = false)
    private Long createTime;

    @NotNull
    @Column(nullable = false)
    private Boolean completed = false;

    @NotNull
    @Column(name = "complete_time", nullable = false)
    private Long completeTime = 0L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Long getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Long completeTime) {
        this.completeTime = completeTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Todo todo = (Todo) o;

        if (!completeTime.equals(todo.completeTime)) return false;
        if (!completed.equals(todo.completed)) return false;
        if (!createTime.equals(todo.createTime)) return false;
        if (id != null ? !id.equals(todo.id) : todo.id != null) return false;
        if (!task.equals(todo.task)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + task.hashCode();
        result = 31 * result + createTime.hashCode();
        result = 31 * result + completed.hashCode();
        result = 31 * result + completeTime.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", task='" + task + '\'' +
                ", createTime=" + createTime +
                ", completed=" + completed +
                ", completeTime=" + completeTime +
                '}';
    }
}
