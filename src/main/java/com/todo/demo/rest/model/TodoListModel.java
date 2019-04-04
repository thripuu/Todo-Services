package com.todo.demo.rest.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TodoListModel {
    private String id;

    @NotNull
    private String name;

    private int nbTodos;
}
