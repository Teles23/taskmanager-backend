package com.datahub.taskmanager.controller;

import com.datahub.taskmanager.dto.*;
import com.datahub.taskmanager.model.User;
import com.datahub.taskmanager.service.UserService;
import com.datahub.taskmanager.utils.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Listar todos os usuários
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Buscar usuário por ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // Criar um novo usuário
    @PostMapping
    public User createUser(@RequestBody User user) {

        return userService.createUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequest) {
        Optional<User> userOptional = userService.findByEmail(loginRequest.getEmail());

        if (userOptional.isEmpty() ||
                !PasswordUtil.verifyPassword(loginRequest.getPassword(), userOptional.get().getPassword())) {
            return ResponseEntity.status(401).body(new LoginResponseDTO(null, "Credenciais inválidas"));
        }

        User user = userOptional.get();

        // Gera o token JWT
        String token = JwtUtil.generateToken(user.getId());

        return ResponseEntity.ok(new LoginResponseDTO(token, "Login realizado com sucesso"));
    }

    // Atualizar usuário
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    // Deletar usuário
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
