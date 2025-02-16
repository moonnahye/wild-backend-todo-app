package com.example.demo.presentation.dto;

public class TaskRequestDto {
    private String content;

    public TaskRequestDto() {
    }
    public TaskRequestDto(String content) {
        this.content = content;
    }


    public String getContent() {
        return content;
    }
}
