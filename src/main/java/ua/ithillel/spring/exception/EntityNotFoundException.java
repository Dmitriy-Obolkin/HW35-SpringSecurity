package ua.ithillel.spring.exception;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(String msg){
        super(msg);
    }
}
