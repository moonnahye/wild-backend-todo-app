package com.example.demo.presentation.dto;

import java.util.List;

public class TodoListResponseDto {

    private List<TodoResponseDto> todoList;

    public TodoListResponseDto(List<TodoResponseDto> todoList) {
        this.todoList = todoList;
    }

    public List<TodoResponseDto> getTodoList() {
        return todoList;
    }
}
