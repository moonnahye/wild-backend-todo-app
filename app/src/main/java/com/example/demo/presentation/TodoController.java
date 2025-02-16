package com.example.demo.presentation;

import com.example.demo.application.TodoService;
import com.example.demo.data.Todo;
import com.example.demo.presentation.dto.TodoListResponseDto;
import com.example.demo.presentation.dto.TodoRequestDto;
import com.example.demo.presentation.dto.TodoResponseDto;
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
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public TodoListResponseDto list() {

        List<Todo> todoList = todoService.getTodoList();

        return new TodoListResponseDto(todoList.stream()
                .map(todo -> new TodoResponseDto(
                        todo.getId(),
                        todo.getContent(),
                        todo.isDone()
                )).toList()
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TodoResponseDto create(
            @RequestBody TodoRequestDto requestDto) {

        Todo todo = todoService.make(requestDto.getContent());

        return new TodoResponseDto(
                todo.getId(), todo.getContent(), todo.isDone());
    }

    @PutMapping("/{id}")
    public TodoResponseDto update(@PathVariable int id) {

        Todo findTodo = todoService.getTodo(id);
        Todo updateTodo = todoService.updateTodo(findTodo.getId());

        return new TodoResponseDto(
                updateTodo.getId(),
                updateTodo.getContent(),
                updateTodo.isDone()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        todoService.deleteTodo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
