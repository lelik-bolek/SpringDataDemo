package ru.home.training.java.cetrification.spring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.home.training.java.cetrification.exseptions.PersonNotFoundExseption;
import ru.home.training.java.cetrification.spring.model.*;
import ru.home.training.java.cetrification.spring.repositories.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    // Добавление нового человека
    public Person addPerson(Person person) {
        return personRepository.save(person);
    }

    // Получение списка всех людей
    public List<Person> getAllPersons() {
        return personRepository.findPersonsWhithAddresses(); //Использует @Query(Select ...)
    }

    // Поиск человека по ID
    public Person getPersonById(Long id) {
        Optional<Person> person = personRepository.findById(id);
        return person.orElse(null); // Возвращает null, если человек не найден
    }

    // Обновление данных человека
    public Person updatePerson(Long id, Person personDetails) {
        Person person = personRepository.findById(id).orElse(null);
        if (person != null) {
            person.setName(personDetails.getName());
            person.setSurname(personDetails.getSurname());
            personRepository.save(person);
        }
        return person; // Возвращает обновленные данные или null,
        // если человек не найден
    }

    // Удаление человека по ID
    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }
    
    // Добавление адреса 
    public void addAddressToPerson(Long id, String city, String street) {
        Person person = personRepository.findById(id).orElse(null);
        if(person != null){
            Address address = new Address();
            address.setCity(city);
            address.setStreet(street);
            person.setAddress(address);
            personRepository.save(person);
        } else {
            throw new PersonNotFoundExseption(id);
        }
    }
        
    // Поиск по имени
    public List<Person> getPersonByName (String name) {
        return personRepository.findByName(name);
    }
        
    //поиск по диапазону возраста 
    public List<Person> getPersonByAgeBetween(Integer min, Integer max) {
        return personRepository.findByAgeBetween(min, max);
    }

    //Писк по городу
    public List<Person> getPersonByCity(String city) {
        return personRepository.findByAddressCity(city);
    }

}
