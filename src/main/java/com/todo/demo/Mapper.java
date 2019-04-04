package com.todo.demo;

import com.todo.demo.dao.TodoListDAO;
import com.todo.demo.model.Todo;
import com.todo.demo.model.TodoList;
import com.todo.demo.rest.model.TodoListModel;
import com.todo.demo.rest.model.TodoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public class Mapper {
    @Autowired
    private TodoListDAO todoListDAO;

    public TodoModel convertToTodoModel(Todo todo) {
        var todoModel = new TodoModel();
        todoModel.setTitle(todo.getTitle());
        todoModel.setId(todo.getId().toString());
        todoModel.setLastModifiedOn(todo.getLastModifiedOn());
        todoModel.setText(todo.getText());
        todoModel.setTodoListId(todo.getTodoList().getId().toString());

        return todoModel;
    }

    public Todo convertToTodoEntity(TodoModel todoModel) {
        var todoList = this.todoListDAO.findById(UUID.fromString(todoModel.getTodoListId())).get();
        var todo = new Todo(todoModel.getId(), todoModel.getTitle(), todoModel.getText(), todoList);
        return todo;
    }

    public TodoListModel convertToTodoListModel(TodoList todoList) {
        var todoListModel = new TodoListModel();
        todoListModel.setId(todoList.getId().toString());
        todoListModel.setName(todoList.getName());
        todoListModel.setNbTodos(todoList.getTodos().size());
        return todoListModel;
    }

    public TodoList convertToTodoListEntity(TodoListModel todoListModel) {
        var todoList = new TodoList(todoListModel.getId(), todoListModel.getName());
        return todoList;
    }
}

