package com.example.demo.presentation;

import com.example.demo.application.TodoRepository;
import com.example.demo.application.TodoService;
import com.example.demo.data.Todo;
import com.example.demo.presentation.dto.TodoRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TodoService todoService;

    @Test
    void list() throws Exception {

        List<Todo> todoList = List.of(
                new Todo(1, "todo1", false),
                new Todo(2, "todo2", true)
        );

        when(todoService.getTodoList()).thenReturn(todoList);

        mockMvc.perform(get("/todo")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void create() throws Exception {

        TodoRequestDto requestDto = new TodoRequestDto("new");
        Todo createdTodo = new Todo(1, "new", false);

        when(todoService.make("new")).thenReturn(createdTodo);

        mockMvc.perform(post("/todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                )
                .andExpect(status().isCreated());
    }

    @Test
    void update() throws Exception {

        int id = 1;
        Todo todo = new Todo(1, "todo", false);
        Todo updatedTodo = new Todo(1, "todo", true);

        when(todoService.getTodo(id)).thenReturn(todo);
        when(todoService.updateTodo(id)).thenReturn(updatedTodo);

        mockMvc.perform(put("/todo/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedTodo)))
                .andExpect(status().isOk());

    }

    @Test
    void deleteTodo() throws Exception {
        int id = 1;

        Todo todo =  new Todo(1, "todo1", false);
        when(todoService.getTodo(id)).thenReturn(todo);

        mockMvc.perform(delete("/todo/{id}", id))
                .andExpect(status().isNoContent());

        verify(todoService).deleteTodo(id);
    }

    @Test
    void delete_NotExistTodo() throws Exception {

        when(todoService.getTodo(999)).thenReturn(null);

        mockMvc.perform(delete("/todo/{id}", 999))
                .andExpect(status().isNotFound());

        verify(todoService, never()).deleteTodo(999);

    }
}
