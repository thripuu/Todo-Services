package com.todo.demo.rest;

import com.todo.demo.Mapper;
import com.todo.demo.dao.TodoListDAO;
import com.todo.demo.model.TodoList;
import com.todo.demo.rest.model.TodoListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/todoLists")
@CrossOrigin
public class TodoListController {
    @Autowired
    private TodoListDAO todoListDAO;
    @Autowired
    private Mapper mapper;

    @GetMapping("/all")
    public List<TodoList> all() {
        var allCategories = this.todoListDAO.findAll();
        return allCategories;
    }
    @GetMapping("/getDefaultTodoList")
    public TodoList getDefaultTodoList() {
        var allCategories = this.todoListDAO.findByName("Default");
        return allCategories;
    }
    @PostMapping
    public TodoList save(@RequestBody TodoListModel todoListModel,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException();
        }

        var todoListEntity = this.mapper.convertToTodoListEntity(todoListModel);

        // save todoListEntity instance to db
        this.todoListDAO.save(todoListEntity);

        return todoListEntity;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        this.todoListDAO.deleteById(UUID.fromString(id));
    }
}

