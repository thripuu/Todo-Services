package com.todo.demo.rest;

import com.todo.demo.dao.TodoDAO;
import com.todo.demo.dao.TodoListDAO;
import com.todo.demo.Mapper;
import com.todo.demo.model.Todo;
import com.todo.demo.rest.model.TodoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin
public class TodoController {

    @Autowired
    private TodoDAO todoDAO;
    @Autowired
    private TodoListDAO todoListDAO;
    @Autowired
    private Mapper mapper;


    @GetMapping("/all")
    public List<TodoModel> all() {
        var todos = this.todoDAO.findAll();

        // map from entity to view model
        var todoModel = todos.stream()
                .map(todo -> this.mapper.convertToTodoModel(todo))
                .collect(Collectors.toList());

        return todoModel;
    }

    @GetMapping("/byId/{id}")
    public TodoModel byId(@PathVariable String id) {
        var todo = this.todoDAO.findById(UUID.fromString(id)).orElse(null);

        if (todo == null) {
            throw new EntityNotFoundException();
        }

        var todoModel = this.mapper.convertToTodoModel(todo);

        return todoModel;
    }

    @GetMapping("/byTodoList/{todoListId}")
    public List<TodoModel> byTodoList(@PathVariable String todoListId) {
        List<Todo> todos = new ArrayList<>();

        var todoList = this.todoListDAO.findById(UUID.fromString(todoListId));
        if (todoList.isPresent()) {
            todos = this.todoDAO.findAllByTodoList(todoList.get());
        }

        // map to Todo model
        var todosModel = todos.stream()
                .map(todo -> this.mapper.convertToTodoModel(todo))
                .collect(Collectors.toList());

        return todosModel;
    }

    @PostMapping
    public Todo save(@RequestBody TodoModel todoModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException();
        }
        var todo = this.mapper.convertToTodoEntity(todoModel);
        this.todoDAO.save(todo);
        return todo;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        this.todoDAO.deleteById(UUID.fromString(id));
    }
}

