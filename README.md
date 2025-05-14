# Spring Boot H2 Console CRUD Demo

Учебный проект на Java с использованием Spring Boot, H2 Database и Spring Data JPA. Проект демонстрирует работу с консольным интерфейсом (через `CommandLineRunner`) и подключением к внешнему серверу H2 через TCP.

## Цель проекта

Научиться:

- использовать Spring Boot и Spring Data JPA;
- подключаться к внешнему серверу H2;
- создавать CRUD-операции над сущностью `Person`;
- запускать приложение через консоль с помощью `CommandLineRunner`;
- управлять H2 сервером программно.

## Стек технологий

- Java 21  
- Spring Boot  
- Spring Data JPA  
- H2 Database (TCP server mode)  
- Maven  
- VS Code

## Как запустить

### 1. Запустить H2 TCP-сервер

```bash
# из IDE или через java
java -cp target/your-artifact-name.jar ru.home.training.java.cetrification.spring.db.H2Server
Если запускается из IDE — запусти класс H2Server.java с методом main.
```
2. Запустить основное приложение

# также из IDE или через jar
java -jar target/your-artifact-name.jar

Будет запущен интерактивный CLI-интерфейс с меню для управления записями Person.
3. (опционально) Подключиться к H2 через браузер

Если включена H2-консоль, перейдите в:

http://localhost:8080/h2-console

И укажите:
    JDBC URL: jdbc:h2:tcp://localhost:9092/~/test
    User: sa
    Password: (оставить пустым)

## Структура проекта
```
├── src/
│   └── main/
│       └── java/
│           └── ru.home.training.java.cetrification.spring/
│               ├── CommandLineApp.java             # CLI-интерфейс
│               ├── PersonService.java              # Сервис с логикой CRUD для Person
│               ├── db/
│               │   └── H2Server.java               # Запуск H2 TCP-сервера
│               ├── model/
│               │   ├── Person.java                 # Сущность Person
│               │   └── Address.java                # Сущность Address
│               ├── repository/
│               │   ├── PersonRepository.java       # Spring Data репозиторий для Person
│               │   └── AddressRepository.java      # Spring Data репозиторий для Address
│               └── resources/
│                   └── application.properties
│
├── pom.xml
├── README.md
└── .gitignore

```