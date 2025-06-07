# 🌟 Gestão de Abrigos - Sistema para Eventos Extremos - SafeStock

## 🌍 Visão Geral
Sistema web para **gerenciamento emergencial** de abrigos durante desastres naturais, com:
- Monitoramento em tempo real
- Alocação inteligente de recursos
- Alertas automatizados
- Dashboard interativo

## 🛠 Tecnologias

**Backend:**
- Java 17
- Spring Boot 3.2
- Spring Security
- Oracle Database

**Frontend:**
- Thymeleaf
- Bootstrap 5
- Chart.js

**Integrações:**
- Spring AI (OpenAI)
- RabbitMQ
- OAuth2 (Google)

## 🚀 Funcionalidades

✅ **Gestão Completa de Abrigos**  
- Cadastro com geolocalização  
- Controle de capacidade  
- Mapa interativo  

✅ **Sistema de Recursos**  
- Controle de estoque  
- Alertas automáticos  
- Distribuição inteligente  

✅ **Segurança**  
- Autenticação via Google  
- Controle de acesso  
- Criptografia de dados  

## 💻 Instalação

1. Clone o repositório:
```bash
git clone https://github.com/GLOnodera/GS_JAVA2025.git
cd gestao-abrigos
```

2. Instale as dependências e configure o banco Oracle no application.properties.

3. Execute a aplicação:
```bash
./mvnw spring-boot:run
```

## 🧪 Testes

```bash
mvn test
```
Principais Testes:

- AbrigoServiceTest (Testes Unitários)
- RecursoControllerTest (Testes de Integração)
- SecurityTest (Testes de Autenticação)

## 🧱 Estrutura

```bash
gestao-abrigos/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── gestaoabrigos/
│   │   │           ├── config/
│   │   │           │   ├── SecurityConfig.java
│   │   │           │   └── WebMvcConfig.java
│   │   │           ├── controller/
│   │   │           │   ├── AbrigoController.java
│   │   │           │   ├── DashboardController.java
│   │   │           │   └── RecursoController.java
│   │   │           ├── dto/
│   │   │           │   ├── RecursoDisponivelDTO.java
│   │   │           │   └── RecursoAbrigoDTO.java
│   │   │           ├── model/
│   │   │           │   ├── Abrigo.java
│   │   │           │   ├── Recurso.java
│   │   │           │   └── DistribuicaoRecurso.java
│   │   │           ├── repository/
│   │   │           │   ├── AbrigoRepository.java
│   │   │           │   └── RecursoRepository.java
│   │   │           ├── service/
│   │   │           │   ├── AbrigoService.java
│   │   │           │   └── RecursoService.java
│   │   │           └── GestaoAbrigosApplication.java
│   │   └── resources/
│   │       ├── static/
│   │       │   ├── css/
│   │       │   │   └── style.css
│   │       │   └── js/
│   │       │       └── dashboard.js
│   │       ├── templates/
│   │       │   ├── dashboard.html
│   │       │   ├── abrigos.html
│   │       │   └── recursos.html
│   │       ├── application.properties
│   │       └── messages.properties
│   └── test/
│       └── java/
│           └── com/
│               └── gestaoabrigos/
│                   └── service/
│                       └── AbrigoServiceTest.java
├── pom.xml
└── README.md
```

## 🌐 Rotas Principais

| Rota           | Descrição                        | Acesso      |
| -------------- | -------------------------------- | ----------- |
| `/dashboard`   | Painel de controle               | Público\*   |
| `/abrigos`     | Lista de abrigos                 | Autenticado |
| `/recursos`    | Gestão de estoques               | Autenticado |
| `/api/alertas` | Endpoint para alertas (RabbitMQ) | Sistema     |

