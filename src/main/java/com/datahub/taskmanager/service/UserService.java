package com.datahub.taskmanager.service;

import com.datahub.taskmanager.exception.UserNotFoundException;
import com.datahub.taskmanager.model.User;
import com.datahub.taskmanager.repository.UserRepository;
import com.datahub.taskmanager.utils.PasswordUtil;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service // Indica que essa classe é um serviço do Spring
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Listar todos os usuários
    public List<User> getAllUsers() {
        return userRepository.findAll();

    }

    // Buscar usuário por ID
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário com ID " + id + " não encontrado"));
    }

    // Criar usuário
    public User createUser(User user) {

        user.setPassword(PasswordUtil.hashPassword(user.getPassword()));
        return userRepository.save(user);
    }

    public boolean verifyUserPassword(String rawPassword, String hashedPassword) {
        return PasswordUtil.verifyPassword(rawPassword, hashedPassword);
    }

    // Atualizar usuário
    public User updateUser(Long id, User userDetails) {
        User user = getUserById(id);
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        return userRepository.save(user);
    }

    // Deletar usuário
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
