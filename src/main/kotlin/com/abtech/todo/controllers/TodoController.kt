package com.abtech.todo.controllers

import com.abtech.todo.entities.Todo
import com.abtech.todo.entities.TodoRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/todos")
@CrossOrigin(origins = ["*"])
class TodoController(val todoRepository: TodoRepository) {

    @GetMapping
    fun getTodos(): List<Todo> = todoRepository.findAll()

    @GetMapping("/{todoId}")
    fun getTodo(@PathVariable("todoId") todoId: Long): Optional<Todo> {
        return todoRepository.findById(todoId)
    }

    @PutMapping("/{todoId}")
    fun updateTodo(@PathVariable("todoId") todoId: Long, @RequestBody updatedTodo: Todo): Todo? {
        val oldTodo = todoRepository.findByIdOrNull(todoId)
        if(oldTodo == null) {
            return oldTodo
        }
        return todoRepository.save(updatedTodo)
    }

    @PostMapping
    fun newTodo(@RequestBody todo: Todo): Todo {
        return todoRepository.save(todo)
    }

    @DeleteMapping("/{todoId}")
    fun deleteTodo(@PathVariable("todoId") todoId: Long) {
        todoRepository.deleteById(todoId)
    }
}