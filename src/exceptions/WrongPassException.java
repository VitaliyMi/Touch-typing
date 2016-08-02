package exceptions;

/**
 * Created by MSI on 02.03.2016.
 */
public class WrongPassException extends CustomException {
    public String message = "Login and password you've do not match";

    @Override
    public String getMessage() {
        return message;
    }
}
