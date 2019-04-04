package com.todo.demo.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class Todo {
    @Id
    private UUID id;
    private String title;
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    private TodoList todoList;

    private Date lastModifiedOn;

    protected Todo() {
        this.id = UUID.randomUUID();
        this.lastModifiedOn = new Date();
    }

    public Todo(String title, String text, TodoList todoList) {
        this();
        this.title = title;
        this.text = text;
        this.todoList = todoList;
    }

    public Todo(String id, String title, String text, TodoList todoList) {
        this(title, text, todoList);
        if (id != null) {
            this.id = UUID.fromString(id);
        }
    }

}