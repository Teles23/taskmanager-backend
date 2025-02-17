package com.datahub.taskmanager.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.datahub.taskmanager.exception.TaskNotFoundException;
import com.datahub.taskmanager.exception.UnauthorizedAccessException;
import com.datahub.taskmanager.model.Task;
import com.datahub.taskmanager.model.TaskStatus;
import com.datahub.taskmanager.model.User;
import com.datahub.taskmanager.repository.TaskRepository;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getTasksByUser(Long userId, TaskStatus status, Sort sort) {
        if (status != null) {
            return taskRepository.findByCreatorIdAndStatus(userId, status, sort);
        }
        return taskRepository.findByCreatorId(userId, sort);
    }

    // Buscar tarefa por ID
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Tarefa não encontrada"));
    }

    // Criar uma nova tarefa
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    // Atualizar uma tarefa (apenas o criador pode editar)
    public Task updateTask(Long id, Task taskDetails, User creator) {
        Task task = getTaskById(id);

        if (!task.getCreator().equals(creator)) {
            throw new UnauthorizedAccessException("Você não tem permissão para editar esta tarefa");
        }

        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setStatus(taskDetails.getStatus());
        task.setDueDate(taskDetails.getDueDate());

        return taskRepository.save(task);
    }

    // Excluir uma tarefa (apenas o criador pode excluir)
    public void deleteTask(Long id, User creator) {
        Task task = getTaskById(id);

        if (!task.getCreator().equals(creator)) {
            throw new UnauthorizedAccessException("Você não tem permissão para excluir esta tarefa");
        }

        taskRepository.delete(task);
    }
}
