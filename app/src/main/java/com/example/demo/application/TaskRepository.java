package com.example.demo.application;

import com.example.demo.data.Task;

import java.util.List;

public interface TaskRepository {

    void add(Task task);

    List<Task> getAll();

    Task findById(int id);

    void delete(int id);
}
