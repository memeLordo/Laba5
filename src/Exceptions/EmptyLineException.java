package Exceptions;

public class EmptyLineException extends RuntimeException {
    public EmptyLineException(String message) {
        super(message+" не может быть пустым.");
//        super(message);
    }


}
