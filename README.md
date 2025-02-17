# Task Manager - Backend (Spring Boot)

## 📌 Descrição
O **Task Manager API** é um backend desenvolvido com **Spring Boot**, responsável pelo gerenciamento de usuários, tarefas e autenticação JWT.

Ele é usado pelo frontend desenvolvido com **React + TypeScript**:
🔗 **[Task Manager - Frontend](https://github.com/Teles23/taskmanager-frontend)**

---

## 🚀 **Principais Recursos**
- 🛠 **CRUD de Tarefas**
- 🔐 **Autenticação JWT**
- 👤 **Cadastro e login de usuários**
- 🔍 **Filtros e ordenação de tarefas**
- 🛡 **Proteção de rotas com Spring Security**
- 🌍 **Configuração de CORS para integração com o frontend**
- 🐳 **Docker para fácil deploy**

---

## 🛠 **Pré-requisitos**
Antes de rodar o backend, você precisa ter instalado:
- **Java 17+**
- **Maven**
- **Docker e Docker Compose (caso use a configuração com Docker)**

---

# 📦 **Configuração do Banco de Dados**
A API suporta **PostgreSQL** e **H2 Database** (banco em memória para testes).

### **🔹 Opção 1: Configuração Manual (PostgreSQL)**
Se quiser rodar o **PostgreSQL localmente**, edite `src/main/resources/application.properties`:

```properties
# Configuração do banco de dados PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/taskmanager
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
