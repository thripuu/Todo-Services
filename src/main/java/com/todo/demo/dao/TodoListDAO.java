package com.todo.demo.dao;

import com.todo.demo.model.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TodoListDAO extends JpaRepository<TodoList, UUID> {

    TodoList findByName(String s);
}
