package exceptions;

public class PetValidateException extends RuntimeException{
    public PetValidateException(String message) {
        super(message);
    }
}
