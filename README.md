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

## 📦 **Configuração do Banco de Dados**
A API suporta **PostgreSQL** .

### **🔹 Opção 1: Configuração Manual (PostgreSQL)**
Se quiser rodar o **PostgreSQL localmente**, edite `src/main/resources/application.properties`:

```properties
# Configuração do banco de dados PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/taskmanager
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### **🔹 Opção 2: Usando Docker (PostgreSQL)**
Se preferir usar o banco de dados via Docker, utilize o seguinte `docker-compose.yml`:

```yaml
version: '3.8'
services:
  postgres:
    image: postgres:15
    container_name: taskmanager_db
    restart: always
    environment:
      POSTGRES_DB: taskmanager
      POSTGRES_USER: admin
      POSTGRES_PASSWORD: admin
    ports:
      - "5436:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data

volumes:
  postgres_data:
```

Para iniciar o banco via Docker, execute:
```sh
docker-compose up -d
```

---

## ▶️ **Rodando o Backend**
### **1️⃣ Clonar o Repositório**
```sh
git clone https://github.com/Teles23/taskmanager-backend.git
cd taskmanager-backend
```

### **2️⃣ Configurar as Variáveis de Ambiente**
Crie um arquivo `.env` na raiz do projeto e defina as configurações:

```env
DATABASE_PASSWORD=admin
JWT_SECRET=minha_chave_secreta
```

### **3️⃣ Construir e Rodar o Projeto**
```sh
mvn clean install
mvn spring-boot:run
```


A API estará rodando em: **http://localhost:8080**

---

## 📜 **Endpoints da API**

### 🔐 **Autenticação**
- `POST /api/users` → Cadastro de usuário
- `POST /api/users/login` → Login e geração de token JWT

### 📌 **Tarefas**
- `GET /api/tasks/user/{userID}` → Lista todas as tarefas (com filtros e ordenação)
- `POST /api/tasks/user/{userID}` → Criação de uma nova tarefa
- `PUT /api/tasks{id}/user/{userID}` → Atualização de uma tarefa
- `DELETE /api/tasks/{id}` → Exclusão de uma tarefa

### 👤 **Usuários**
- `GET /api/users/{id}` → Busca um usuário específico
- `PUT /api/users/{id}` → Atualização de dados do usuário

---

## 🔍 **Testes**
O projeto contém testes unitários para a camada de serviço.
Para rodar os testes, execute:
```sh
mvn test
```

---

## 🏗 **Decisões Técnicas**
- **Spring Boot 3 + Java 17** para um backend robusto e moderno
- **Spring Security + JWT** para autenticação segura
- **Spring Data JPA + PostgreSQL** para persistência eficiente
- **Docker** para facilitar o deploy e a configuração do ambiente
- **H2 Database** para testes sem necessidade de configurar banco externo
- **Testes Unitários** para garantir confiabilidade do código

---

## 🚀 **Frontend**
Para rodar o frontend do projeto, siga as instruções no repositório:
🔗 **[Task Manager - Frontend](https://github.com/Teles23/taskmanager-frontend)**

---

