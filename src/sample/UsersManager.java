package sample;

import java.io.*;

import exceptions.NoSuchUserException;
import exceptions.UserAlreadyExists;
import exceptions.WrongPassException;

/**
 * Created by MSI on 06.12.2015.
 */
public class UsersManager {
    private String pass;
    private String login;
    private int currentLVL;
    User user;

    public void checkPass(String log, String pass) throws WrongPassException, NoSuchUserException{
        File file = new File("src\\users\\" + log + ".txt");
        try {
            if (file.exists()) {

                ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file));


                user = (User) stream.readObject();

                if (user.getPass().equals(pass)) {
                    currentLVL=user.getLvl();
                    login = user.getName();
                    pass = user.getPass();
                }
                else throw new WrongPassException();
            }
            else throw new NoSuchUserException();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }

    }

    public void createUser(String login, String pass)  throws UserAlreadyExists{
        this.currentLVL = 1;
        ObjectOutputStream writer = null;
        File file = new File("src\\users\\" + login+".txt");
        try {
            if (file.createNewFile()) {
                writer = new ObjectOutputStream(new FileOutputStream(file));
                user = new User(login,pass,1);
                writer.writeObject(user);
                writer.close();
            }
            else throw new UserAlreadyExists();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(int lvl) {
        System.out.println(lvl+" "+ currentLVL);
        if (lvl == currentLVL && currentLVL < 11) {
            currentLVL++;
            try {
                ObjectInputStream reader = new ObjectInputStream(new FileInputStream("src\\users\\" + login + ".txt"));
                User user =(User) reader.readObject();
                user.levelPassed();
                reader.close();
                ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream("src\\users\\" + login + ".txt"));
                writer.writeObject(user);
                writer.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            catch (ClassNotFoundException e)
            {
                    e.printStackTrace();
            }
        }
    }

    public int getCurrentLVL() {
        return currentLVL;
    }
}
