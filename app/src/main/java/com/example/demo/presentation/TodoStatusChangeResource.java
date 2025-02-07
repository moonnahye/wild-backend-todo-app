package com.example.demo.presentation;

import com.example.demo.application.TodoCreator;
import com.example.demo.data.Todo;
import com.example.demo.presentation.dto.TodoChangeRequestDto;
import com.example.demo.presentation.dto.TodoResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TodoStatusChangeResource implements ResourceHandler{

    private final TodoCreator todoCreator = new TodoCreator();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String handle(String content) throws JsonProcessingException {

        TodoChangeRequestDto requestDto = objectMapper.readValue(
                content, TodoChangeRequestDto.class);

        Todo findTodo = todoCreator.getTodo(requestDto.getId());
        Todo updateTodo = todoCreator.update(findTodo.getId());

        return objectMapper.writeValueAsString(
                new TodoResponseDto(
                        updateTodo.getId(),
                        updateTodo.getContent(),
                        updateTodo.isDone()
                ));
    }
}
