package com.example.demo.application;

import com.example.demo.data.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class TodoServiceTest {

    private TodoService todoService;
    private TodoRepository todoRepository;

    @BeforeEach
    void setUp() {
        todoRepository = mock(TodoRepository.class);
        todoService = new TodoService(todoRepository);
    }

    @Test
    void createTodo() {
        String content = "new";
        doNothing().when(todoRepository).add(any());

        Todo createdTodo = todoService.make(content);

        assertThat(createdTodo.getContent()).isEqualTo(content);
        assertThat(createdTodo.isDone()).isFalse();

        verify(todoRepository).add(any());
    }

    @Test
    void getTodoById_Success() {
        int id = 1;
        Todo todo1 = new Todo(id, "todo1", false);

        when(todoRepository.getTodo(id)).thenReturn(todo1);

        Todo result = todoService.getTodo(id);

        assertThat(result.getId()).isEqualTo(id);
        assertThat(result.getContent()).isEqualTo("todo1");
        assertThat(result.isDone()).isFalse();

        verify(todoRepository).getTodo(id);
    }

    @Test
    void getTodoById_Fail() {
        int id = 999;
        when(todoRepository.getTodo(id)).thenReturn(null);

        Todo result = todoService.getTodo(id);

        assertThat(result).isNull();

        verify(todoRepository).getTodo(id);
    }

    @Test
    void getAllTodo() {
        Todo todo1 = new Todo(1, "todo1", false);
        Todo todo2 = new Todo(2, "todo2", true);
        when(todoRepository.getAll()).thenReturn(List.of(todo1, todo2));

        List<Todo> todoList = todoService.getTodoList();

        assertThat(todoList).hasSize(2);

        verify(todoRepository).getAll();
    }

    @Test
    void updateTodo() {
        Todo todo = new Todo(1, "change", false);
        when(todoRepository.getTodo(1)).thenReturn(todo);

        Todo result = todoService.updateTodo(1);

        assertThat(result.getId()).isEqualTo(1);
        assertThat(result.getContent()).isEqualTo("change");
        assertThat(result.isDone()).isTrue();

        verify(todoRepository).getTodo(1);
    }

    @Test
    void deleteTodo(){
        int id = 1;
        doNothing().when(todoRepository).delete(id);

        todoService.deleteTodo(id);

        verify(todoRepository).delete(id);
    }

}
