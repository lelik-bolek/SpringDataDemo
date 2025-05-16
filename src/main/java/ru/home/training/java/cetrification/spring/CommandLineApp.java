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

    /**
     * Главный метод приложения, запускающий Spring Boot приложение
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        System.out.println("Кодировка: " + System.getProperty("file.encoding"));
        SpringApplication.run(CommandLineApp.class, args);
    }

    /**
     * Создает и возвращает CommandLineRunner для обработки команд пользователя
     * @return CommandLineRunner с основной логикой приложения
     */
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
                System.out.println("7 - Найти людей по городу проживания");
                System.out.println("8 - Найти людей по возрасту");
                System.out.println("9 - Найти людей по имени");
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
                    case 7: 
                        getPersonByCity(scanner);
                        break;
                    case 8: 
                        getPersonByAgeBetween(scanner);   
                        break;
                    case 9: 
                        getPersonByName(scanner);   
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

    /**
     * Поиск людей по имени
     * @param scanner объект Scanner для ввода данных
     */
    private void getPersonByName(Scanner scanner) {
        System.out.println("Введите имя");
        String name = scanner.nextLine();
        List<Person> persons = personService.getPersonByName(name);
        if(persons.isEmpty()){
            System.out.println("Люди с именем:" + name + " не найдены!");
        } else {
            persons.forEach(System.out::println);
        }
    }

    /**
     * Поиск людей по диапазону возрастов
     * @param scanner объект Scanner для ввода данных
     */
    private void getPersonByAgeBetween(Scanner scanner) {
        System.out.println("Введите минимальный возраст: min");
        int minAge = scanner.nextInt();
        System.out.println("Введите максимальный возраст: max");
        int maxAge = scanner.nextInt();
        List<Person> persons = personService.getPersonByAgeBetween(minAge, maxAge);
        if(persons.isEmpty()){
            System.out.println("Люди в диапазоне возрастов от :" + minAge + " до " +  maxAge + " не найдены!");
        } else {
            persons.forEach(System.out::println);
        }
    }

    /**
     * Поиск людей по городу проживания
     * @param scanner объект Scanner для ввода данных
     */
    private void getPersonByCity(Scanner scanner) {
        System.out.println("Введите город");
        String city = scanner.nextLine();
        List<Person> persons = personService.getPersonByCity(city);
        if(persons.isEmpty()){
            System.out.println("Люди в городе:" + city + " не найдены!");
        } else {
            persons.forEach(System.out::println);
        }
    }

    /**
     * Добавление нового человека
     * @param scanner объект Scanner для ввода данных
     */
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

    /**
     * Отображение всех людей в системе
     */
    private void showAllPersons() {
        List<Person> persons = personService.getAllPersons();
        persons.forEach(System.out::println);
    }

    /**
     * Поиск человека по ID
     * @param scanner объект Scanner для ввода данных
     */
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

    /**
     * Обновление данных человека
     * @param scanner объект Scanner для ввода данных
     */
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

    /**
     * Удаление человека по ID
     * @param scanner объект Scanner для ввода данных
     */
    private void deletePerson(Scanner scanner) {
        System.out.println("Введите ID человека для удаления:");
        Long id = scanner.nextLong();
        personService.deletePerson(id);
        System.out.println("Человек удален.");
    }

    /**
     * Добавление адреса человеку
     * @param scanner объект Scanner для ввода данных
     */
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

