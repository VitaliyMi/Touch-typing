package exceptions;

/**
 * Created by MSI on 03.03.2016.
 */
public class UserAlreadyExists extends CustomException{
   public String message = "User with this login is already exists";

   public String getMessage()
   {
      return message;
   }

}
