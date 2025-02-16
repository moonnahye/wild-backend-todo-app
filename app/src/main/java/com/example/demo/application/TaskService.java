package com.example.demo.application;

import com.example.demo.data.Task;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskService {

    private final TaskRepository taskRepository;
    private int sequence = 0;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task make(String content) {
        Task task = new Task(++sequence, content, false);
        taskRepository.add(task);
        return task;
    }

    public List<Task> getTasks() {
        return taskRepository.getAll();
    }

    public Task getTask(int id) {
        Task task = taskRepository.findById(id);
        return task == null ? null : task;
    }

    public Task updateTask(int id) {
        Task task = taskRepository.findById(id);
        task.changeStatus();
        return task;
    }

    public void deleteTask(int id) {
        taskRepository.delete(id);
    }
}
