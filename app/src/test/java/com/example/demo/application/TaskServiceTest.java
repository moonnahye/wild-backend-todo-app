package com.example.demo.application;

import com.example.demo.data.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class TaskServiceTest {

    private TaskService taskService;
    private TaskRepository taskRepository;

    @BeforeEach
    void setUp() {
        taskRepository = mock(TaskRepository.class);
        taskService = new TaskService(taskRepository);
    }

    @Test
    void createTodo() {
        String content = "new";
        doNothing().when(taskRepository).add(any());

        Task createdTodo = taskService.make(content);

        assertThat(createdTodo.getContent()).isEqualTo(content);
        assertThat(createdTodo.isDone()).isFalse();

        verify(taskRepository).add(any());
    }

    @Test
    void getTaskById_Success() {
        int id = 1;
        Task todo1 = new Task(id, "todo1", false);

        when(taskRepository.findById(id)).thenReturn(todo1);

        Task result = taskService.getTask(id);

        assertThat(result.getId()).isEqualTo(id);
        assertThat(result.getContent()).isEqualTo("todo1");
        assertThat(result.isDone()).isFalse();

        verify(taskRepository).findById(id);
    }

    @Test
    void getTaskById_Fail() {
        int id = 999;
        when(taskRepository.findById(id)).thenReturn(null);

        Task result = taskService.getTask(id);

        assertThat(result).isNull();

        verify(taskRepository).findById(id);
    }

    @Test
    void getAllTodo() {
        Task todo1 = new Task(1, "todo1", false);
        Task todo2 = new Task(2, "todo2", true);
        when(taskRepository.getAll()).thenReturn(List.of(todo1, todo2));

        List<Task> todoList = taskService.getTasks();

        assertThat(todoList).hasSize(2);

        verify(taskRepository).getAll();
    }

    @Test
    void updateTask() {
        Task todo = new Task(1, "change", false);
        when(taskRepository.findById(1)).thenReturn(todo);

        Task result = taskService.updateTask(1);

        assertThat(result.getId()).isEqualTo(1);
        assertThat(result.getContent()).isEqualTo("change");
        assertThat(result.isDone()).isTrue();

        verify(taskRepository).findById(1);
    }

    @Test
    void deleteTask(){
        int id = 1;
        doNothing().when(taskRepository).delete(id);

        taskService.deleteTask(id);

        verify(taskRepository).delete(id);
    }

}
