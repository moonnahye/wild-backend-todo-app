package com.example.demo.presentation.dto;

public class TodoRequestDto {
    private String content;

    public TodoRequestDto() {
    }
    public TodoRequestDto(String content) {
        this.content = content;
    }


    public String getContent() {
        return content;
    }
}
