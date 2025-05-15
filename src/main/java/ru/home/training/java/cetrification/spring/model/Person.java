package ru.home.training.java.cetrification.spring.model;


import jakarta.persistence.*;

@Entity
@Table(name = "persons") // Таблица в БД
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Автоинкремент
    private Long id;

    private String name;

    private String surname;

    private Integer age;

    // Внешний ключ на таблицу addresses (поле address_id будет создано в таблице persons)
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id") // Название столбца
    private Address address;

    public Person() {
    }

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        String addressString = "aдрес не указан";

        if(address != null){
            addressString= address.getCity() + ", " + address.getStreet();
        }

        return "Person {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age='" + age + '\'' +
                "} проживает по адресу: " +  addressString;
    }
}
