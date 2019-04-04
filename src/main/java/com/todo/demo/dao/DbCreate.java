package com.todo.demo.dao;

import com.todo.demo.model.Todo;
import com.todo.demo.model.TodoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "todo.db.recreate", havingValue = "true")
public class DbCreate implements CommandLineRunner {

    @Autowired
    private TodoListDAO todoListDAO;
    @Autowired
    private TodoDAO todoDAO;

    @Override
    public void run(String... args) {

       // this.todoDAO.deleteAll();
       // this.todoListDAO.deleteAll();

        var defaultTodoList = new TodoList("Default");
        this.todoListDAO.save(defaultTodoList);

        var choresTodoList = new TodoList("Chores");
        this.todoListDAO.save(choresTodoList);

        var todo = new Todo("DataLake Services", "Pivotal Story #879888", defaultTodoList);
        this.todoDAO.save(todo);

        var choresTodo = new Todo("Chores", "Serach by DDMM", choresTodoList);
        this.todoDAO.save(choresTodo);

        System.out.println("Initialized database");
    }
}

