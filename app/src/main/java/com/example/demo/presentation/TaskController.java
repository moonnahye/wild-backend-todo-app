package com.example.demo.presentation;

import com.example.demo.application.TaskService;
import com.example.demo.data.Task;
import com.example.demo.presentation.dto.TaskListResponseDto;
import com.example.demo.presentation.dto.TaskRequestDto;
import com.example.demo.presentation.dto.TaskResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService todoService) {
        this.taskService = todoService;
    }

    @GetMapping
    public TaskListResponseDto list() {

        List<Task> todoList = taskService.getTasks();

        return new TaskListResponseDto(todoList.stream()
                .map(task -> new TaskResponseDto(
                        task.getId(),
                        task.getContent(),
                        task.isDone()
                )).toList()
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponseDto create(
            @RequestBody TaskRequestDto requestDto) {

        Task todo = taskService.make(requestDto.getContent());

        return new TaskResponseDto(
                todo.getId(), todo.getContent(), todo.isDone());
    }

    @PutMapping("/{id}")
    public TaskResponseDto update(@PathVariable int id) {

        Task findTask = taskService.getTask(id);
        Task updatedTask = taskService.updateTask(findTask.getId());

        return new TaskResponseDto(
                updatedTask.getId(),
                updatedTask.getContent(),
                updatedTask.isDone()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        Task task = taskService.getTask(id);
        if (task == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
