package com.example.demo.presentation.dto;

public class TodoResponseDto {
    private String content;
    private boolean done;

    public TodoResponseDto(String content, boolean done) {
        this.content = content;
        this.done = done;
    }

    public String getContent() {
        return content;
    }

    public boolean isDone() {
        return done;
    }
}
