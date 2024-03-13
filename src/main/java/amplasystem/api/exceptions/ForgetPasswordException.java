package amplasystem.api.exceptions;

public class ForgetPasswordException extends RuntimeException {

    public ForgetPasswordException(String message) {
        super(message);
    }

    public ForgetPasswordException(String message, Throwable cause) {
        super(message, cause);
    }
}
