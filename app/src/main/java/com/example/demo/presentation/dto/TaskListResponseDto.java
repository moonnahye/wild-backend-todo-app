package com.example.demo.presentation.dto;

import java.util.List;

public class TaskListResponseDto {

    private List<TaskResponseDto> taskResponseDtos;

    public TaskListResponseDto(List<TaskResponseDto> taskResponseDtos) {
        this.taskResponseDtos = taskResponseDtos;
    }

    public List<TaskResponseDto> getTaskResponseDtos() {
        return taskResponseDtos;
    }
}
