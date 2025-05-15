package ru.home.training.java.cetrification.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import ru.home.training.java.cetrification.exseptions.PersonNotFoundExseption;
import ru.home.training.java.cetrification.spring.model.Person;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class CommandLineApp {

    @Autowired
    private PersonService personService;

    public static void main(String[] args) {
        System.out.println("Кодировка: " + System.getProperty("file.encoding"));
        SpringApplication.run(CommandLineApp.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("\nВыберите действие:");
                System.out.println("1 - Добавить человека");
                System.out.println("2 - Показать всех людей");
                System.out.println("3 - Найти человека по ID");
                System.out.println("4 - Обновить данные человека");
                System.out.println("5 - Удалить человека");
                System.out.println("6 - Указать адрес  человека");
                System.out.println("0 - Выход");

                int choice = scanner.nextInt();
                scanner.nextLine(); // очистка буфера

                switch (choice) {
                    case 1:
                        addPerson(scanner);
                        break;
                    case 2:
                        showAllPersons();
                        break;
                    case 3:
                        findPersonById(scanner);
                        break;
                    case 4:
                        updatePerson(scanner);
                        break;
                    case 5:
                        deletePerson(scanner);
                        break;
                    case 6: 
                        addAddressToPerson(scanner);   
                        break;
                    case 0:
                        System.out.println("Выход из программы...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Неверный выбор, попробуйте снова.");
                        break;
                }
            }
        };
    }

    private void addPerson(Scanner scanner) {
        System.out.println("Введите имя:");
        String name = scanner.nextLine();
        System.out.println("Введите фамилию:");
        String surname = scanner.nextLine();
        Person person = new Person(name, surname);
        System.out.println("Введите возраст:");
        int age  = scanner.nextInt();
        person.setAge(age);
        scanner.nextLine(); // очистка буфера 
        personService.addPerson(person);
        System.out.println("Человек добавлен: " + person);
    }

    private void showAllPersons() {
        List<Person> persons = personService.getAllPersons();
        persons.forEach(System.out::println);
    }

    private void findPersonById(Scanner scanner) {
        System.out.println("Введите ID человека:");
        Long id = scanner.nextLong();
        Person person = personService.getPersonById(id);
        if (person != null) {
            System.out.println("Найденный человек: " + person);
        } else {
            System.out.println("Человек с таким ID не найден.");
        }
    }

    private void updatePerson(Scanner scanner) {
        System.out.println("Введите ID человека для обновления:");
        Long id = scanner.nextLong();
        scanner.nextLine(); // очистка буфера
        System.out.println("Введите имя:");
        String name = scanner.nextLine();
        System.out.println("Введите фамилию:");
        String surname = scanner.nextLine();
        Person person = new Person(name, surname);
        Person updatedPerson = personService.updatePerson(id, person);
        if (updatedPerson != null) {
            System.out.println("Данные человека обновлены: " + updatedPerson);
        } else {
            System.out.println("Человек с таким ID не найден.");
        }
    }

    private void deletePerson(Scanner scanner) {
        System.out.println("Введите ID человека для удаления:");
        Long id = scanner.nextLong();
        personService.deletePerson(id);
        System.out.println("Человек удален.");
    }

    private void addAddressToPerson(Scanner scanner) {
        System.out.println("Введите ID человека:");
        Long id = scanner.nextLong();
        scanner.nextLine(); // очистка буфера
        System.out.println("Введите город:");
        String city = scanner.nextLine();
        System.out.println("Введите улицу:");
        String street = scanner.nextLine();
        try{
            personService.addAddressToPerson(id, city, street);
            System.out.println("Адрес добавлен.");
        } catch(PersonNotFoundExseption e) {
            System.out.println(e.getMessage());
        }
        
    }

}

