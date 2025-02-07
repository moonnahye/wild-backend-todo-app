package com.example.demo.presentation;

import com.example.demo.application.TodoManager;
import com.example.demo.data.Todo;
import com.example.demo.presentation.dto.TodoRequestDto;
import com.example.demo.presentation.dto.TodoResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TodoCreateResource implements ResourceHandler {

    private final TodoManager todoManager = new TodoManager();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String handle(String content) throws JsonProcessingException {

        TodoRequestDto requestDto =
                objectMapper.readValue(content, TodoRequestDto.class);

        Todo todo = todoManager.make(requestDto.getContent());

        return objectMapper.writeValueAsString(
                new TodoResponseDto(todo.getId(), todo.getContent(), todo.isDone()));
    }
}
