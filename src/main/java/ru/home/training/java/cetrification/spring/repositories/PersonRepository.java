package ru.home.training.java.cetrification.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.home.training.java.cetrification.spring.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
}

