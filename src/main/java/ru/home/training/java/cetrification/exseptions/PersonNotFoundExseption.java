package ru.home.training.java.cetrification.exseptions;

public class PersonNotFoundExseption extends RuntimeException{
    Long id;
    public PersonNotFoundExseption(long id){
        super("Человек с ID "+ id + " не найден!");
        this.id = id;
    }

    public Long getId() {
        return id;
    }
     
}