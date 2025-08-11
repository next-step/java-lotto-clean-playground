package exception;

public class CustomException extends IllegalArgumentException {

    public CustomException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }
}
