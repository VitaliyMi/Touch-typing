package exceptions;

/**
 * Created by MSI on 03.03.2016.
 */
public abstract class CustomException  extends Exception{
    public String message="12";

    public String getMessage()
    {
        return this.message;
    }
}
