package com.example.demo.presentation;

import com.example.demo.application.TodoCreator;
import com.example.demo.data.Todo;
import com.example.demo.presentation.dto.TodoResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class TodoListResource implements ResourceHandler {

    private final TodoCreator todoCreator = new TodoCreator();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String handle(String content) throws JsonProcessingException {

        List<Todo> todoList = todoCreator.getTodoList();

        return objectMapper.writeValueAsString(todoList.stream()
                .map(todo -> new TodoResponseDto(
                                todo.getContent(),
                                todo.isDone()
                        )
                ).toList()
        );
    }
}
