package com.example.demo.presentation;

import com.example.demo.application.TaskService;
import com.example.demo.data.Task;
import com.example.demo.presentation.dto.TaskRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TaskService taskService;

    @Test
    void list() throws Exception {

        List<Task> todoList = List.of(
                new Task(1, "todo1", false),
                new Task(2, "todo2", true)
        );

        when(taskService.getTasks()).thenReturn(todoList);

        mockMvc.perform(get("/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void create() throws Exception {

        TaskRequestDto requestDto = new TaskRequestDto("new");
        Task createdTodo = new Task(1, "new", false);

        when(taskService.make("new")).thenReturn(createdTodo);

        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                )
                .andExpect(status().isCreated());
    }

    @Test
    void update() throws Exception {

        int id = 1;
        Task todo = new Task(1, "todo", false);
        Task updatedTodo = new Task(1, "todo", true);

        when(taskService.getTask(id)).thenReturn(todo);
        when(taskService.updateTask(id)).thenReturn(updatedTodo);

        mockMvc.perform(put("/tasks/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedTodo)))
                .andExpect(status().isOk());

    }

    @Test
    void deleteTodo() throws Exception {
        int id = 1;

        Task todo =  new Task(1, "todo1", false);
        when(taskService.getTask(id)).thenReturn(todo);

        mockMvc.perform(delete("/tasks/{id}", id))
                .andExpect(status().isNoContent());

        verify(taskService).deleteTask(id);
    }

    @Test
    void delete_NotExistTodo() throws Exception {

        when(taskService.getTask(999)).thenReturn(null);

        mockMvc.perform(delete("/tasks/{id}", 999))
                .andExpect(status().isNotFound());

        verify(taskService, never()).deleteTask(999);
    }
}
