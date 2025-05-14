package ru.home.training.java.cetrification.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.home.training.java.cetrification.spring.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
