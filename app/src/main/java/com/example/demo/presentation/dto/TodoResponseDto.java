package com.example.demo.presentation.dto;

public class TodoResponseDto {
    private int id;
    private String content;
    private boolean done;

    public TodoResponseDto(int id, String content, boolean done) {
        this.id = id;
        this.content = content;
        this.done = done;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public boolean isDone() {
        return done;
    }
}
