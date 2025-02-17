package com.datahub.taskmanager.repository;

import com.datahub.taskmanager.model.Task;
import com.datahub.taskmanager.model.TaskStatus;
import com.datahub.taskmanager.model.User;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByCreator(User creator);

    // Busca todas as tarefas de um usuário específico
    List<Task> findByCreatorId(Long userId, Sort sort);

    // Busca tarefas de um usuário específico filtrando por status
    List<Task> findByCreatorIdAndStatus(Long userId, TaskStatus status, Sort sort);
}
