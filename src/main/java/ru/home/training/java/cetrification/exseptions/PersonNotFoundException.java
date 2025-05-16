package ru.home.training.java.cetrification.exseptions;

/**
 * Исключение, выбрасываемое при попытке найти несуществующего человека
 */
public class PersonNotFoundException extends RuntimeException{
    Long id;

    /**
     * Создает новое исключение с указанием ID не найденного человека
     * @param id ID человека, который не был найден
     */
    public PersonNotFoundException (long id){
        super("Человек с ID "+ id + " не найден!");
        this.id = id;
    }

    public Long getId() {
        return id;
    }
     
}