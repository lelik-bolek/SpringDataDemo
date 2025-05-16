package ru.home.training.java.cetrification.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;
import ru.home.training.java.cetrification.spring.model.Person;
import java.util.List;


public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findByName(String name);

    List<Person> findByAgeBetween(int ageFrom, int ageTo);

    List<Person> findByAddressCity(String city);

    @Query("SELECT p FROM Person p LEFT JOIN FETCH p.address")
    List<Person> findPersonsWhithAddresses();

    //Добавляем +1 год/age к возрасту всех Person
    @Modifying // необходимо для операций изменения UPDATE, Delete
    @Transactional // необходимо для операций @Modifying
    @Query("UPDATE Person p SET p.age = p.age + 1 ")
    void increaseAgeForAllPPersons();

    /*
     * //Вариант1
     * //Удаляет мользователей старше 100 age - через @Query
     * @Modifying 
     * @Transactional 
     * @Query("DELETE FROM Person p WHERE p.age > ?1 ")
     * void removeOver100YearsOld(int retirementAge);
     */
    
    //Вариант2
    //Удаляет мользователей старше 100 age - через синтаксис Имен Метода
    void deleteByAgeGreaterThanEqual(int age);
}

