package ru.home.training.java.cetrification.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ru.home.training.java.cetrification.spring.model.Person;
import java.util.List;


public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findByName(String name);

    List<Person> findByAgeBetween(int ageFrom, int ageTo);

    List<Person> findByAddressCity(String city);

    @Query("SELECT p FROM Person p LEFT JOIN FETCH p.address")
    List<Person> findPersonsWhithAddresses();
}

