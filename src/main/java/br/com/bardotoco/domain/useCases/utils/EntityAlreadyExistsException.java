package main.java.br.com.bardotoco.domain.useCases.utils;

public class EntityAlreadyExistsException extends RuntimeException{
    public EntityAlreadyExistsException(String message) {
        super(message);
    }
}