package ua.ithillel.exception;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(int id){
        super("Order with ID " + id + " not found.");
    }
}
