package com.datahub.taskmanager.controller;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import com.datahub.taskmanager.model.Task;
import com.datahub.taskmanager.model.TaskStatus;
import com.datahub.taskmanager.model.User;
import com.datahub.taskmanager.service.TaskService;
import com.datahub.taskmanager.service.UserService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    private final UserService userService;

    // Listar todas as tarefas de um usuário

    @GetMapping("/user/{userId}") // Filtra por usuário
    public List<Task> getTasksByUser(
            @PathVariable Long userId, // ID do usuário (obrigatório)
            @RequestParam(required = false) TaskStatus status, // Filtro opcional por status
            @RequestParam(required = false, defaultValue = "id") String sortBy, // Campo de ordenação
            @RequestParam(required = false, defaultValue = "asc") String order // Direção da ordenação
    ) {
        Sort.Direction direction = order.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        return taskService.getTasksByUser(userId, status, Sort.by(direction, sortBy));
    }

    // Buscar tarefa por ID
    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    // Criar uma nova tarefa
    @PostMapping("/user/{userId}")
    public Task createTask(@PathVariable Long userId, @RequestBody Task task) {
        User user = userService.getUserById(userId);
        task.setCreator(user);
        return taskService.createTask(task);
    }

    // Atualizar uma tarefa
    @PutMapping("/{id}/user/{userId}")
    public Task updateTask(@PathVariable Long id, @PathVariable Long userId, @RequestBody Task task) {
        User user = userService.getUserById(userId);
        return taskService.updateTask(id, task, user);
    }

    // Excluir uma tarefa
    @DeleteMapping("/{id}/user/{userId}")
    public void deleteTask(@PathVariable Long id, @PathVariable Long userId) {
        User user = userService.getUserById(userId);
        taskService.deleteTask(id, user);
    }

}
