package com.example.demo.presentation;

import com.example.demo.application.TodoManager;
import com.example.demo.data.Todo;
import com.example.demo.presentation.dto.TodoDeleteRequestDto;
import com.example.demo.presentation.dto.TodoResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class TodoDeleteResource implements ResourceHandler{

    private final TodoManager todoManager = new TodoManager();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String handle(String content) throws JsonProcessingException {

        TodoDeleteRequestDto requestDto =
                objectMapper.readValue(content, TodoDeleteRequestDto.class);

        List<Todo> todoList = todoManager.deleteTodo(requestDto.getId());

        return objectMapper.writeValueAsString(todoList.stream()
                .map(todo -> new TodoResponseDto(
                                todo.getId(),
                                todo.getContent(),
                                todo.isDone()
                        )
                ).toList()
        );
    }
}
