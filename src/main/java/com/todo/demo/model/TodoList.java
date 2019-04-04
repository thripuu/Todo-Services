package com.todo.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class TodoList {
    @Id
    private UUID id;
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "todoList", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Todo> todos;


    protected TodoList() {
        this.id = UUID.randomUUID();
        this.todos = new ArrayList<>();
    }

    public TodoList(String name) {
        this();
        this.name = name;
    }

    public TodoList(String id, String name) {
        this();
        if (id != null) {
            this.id = UUID.fromString(id);
        }
        this.name = name;
    }

    public int getNbOfTodos() {
        return this.todos.size();
    }

}

