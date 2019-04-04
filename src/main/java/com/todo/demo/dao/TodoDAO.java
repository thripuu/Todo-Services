package com.todo.demo.dao;

import com.todo.demo.model.Todo;
import com.todo.demo.model.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TodoDAO extends JpaRepository<Todo, UUID> {
    List<Todo> findAllByTodoList(TodoList tooList);
}
