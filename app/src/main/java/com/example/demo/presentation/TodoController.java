package com.example.demo.presentation;

import com.example.demo.application.TodoManager;
import com.example.demo.data.Todo;
import com.example.demo.presentation.dto.TodoListResponseDto;
import com.example.demo.presentation.dto.TodoRequestDto;
import com.example.demo.presentation.dto.TodoResponseDto;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {

    private final TodoManager todoManager;

    public TodoController(TodoManager todoManager) {
        this.todoManager = todoManager;
    }

    @GetMapping
    public TodoListResponseDto list() {

        List<Todo> todoList = todoManager.getTodoList();

        return new TodoListResponseDto(todoList.stream()
                .map(todo -> new TodoResponseDto(
                        todo.getId(),
                        todo.getContent(),
                        todo.isDone()
                )).toList()
        );
    }

    @PostMapping
    public TodoResponseDto create(
            @RequestBody TodoRequestDto requestDto) {

        Todo todo = todoManager.make(requestDto.getContent());

        return new TodoResponseDto(
                todo.getId(), todo.getContent(), todo.isDone());
    }

    @PutMapping("/{id}")
    public TodoResponseDto update(@PathVariable int id) {

        Todo findTodo = todoManager.getTodo(id);
        Todo updateTodo = todoManager.updateTodo(findTodo.getId());

        return new TodoResponseDto(
                updateTodo.getId(),
                updateTodo.getContent(),
                updateTodo.isDone()
        );
    }

    @DeleteMapping("/{id}")
    public TodoListResponseDto delete(@PathVariable int id) {

        List<Todo> todoList = todoManager.deleteTodo(id);

        return new TodoListResponseDto(todoList.stream()
                .map(todo -> new TodoResponseDto(
                                todo.getId(),
                                todo.getContent(),
                                todo.isDone()
                        )
                ).toList()
        );
    }
}
