package sample;


import exceptions.CustomException;
import javafx.stage.Stage;
import view.stages.StageFactory;

import java.util.TreeMap;


public class Controller {
    private Model model;
    private static ViewResolver resolver=new FXViewResolver();
    private long startTime;
    private long endTime;
    private String text;
    private String instruction;
    private UsersManager manager=new UsersManager();
    private boolean finished = false;
    private static StageFactory factory = new StageFactory();
    private int currentLvl;
    private TreeMap<Long, Character> timeTable=new TreeMap();
    private Controller(){}

    private static Controller instance;
    public void start(Stage primaryStage) throws InterruptedException
    {
        factory.createLogInWindow();
    }


    public void showLvl(final int lvl)
    { model=new LevelDownloader("Level "+lvl);

        currentLvl=lvl;
        System.out.println(currentLvl+"in showlvl");
        finished=false;
        text = model.getText();
        instruction=model.getInstruction();
        Stage stage =factory.createLevelScene(text, instruction);
        stage.show();
    }


    public void countTime(String newValue)
    {
        endTime=System.nanoTime();
        long timeForCount = endTime-startTime;
        char c=' ';
        if(newValue.length()>0)
        {
            c= newValue.charAt(newValue.length()-1);
        }
        timeTable.put(timeForCount,c);
        startTime =System.nanoTime();

    }


    public void logIn(String login, String pass)
    {
        try
        {
            manager.checkPass(login,pass);
            resolver.showAvailableLvls(manager.getCurrentLVL());
        }
        catch (CustomException e)
        {
            factory.createLogInWindow();
            factory.showMessage(e);
        }

    }


    public void signIn(String login, String pass)
    {
        try
        {
            manager.createUser(login, pass);
            resolver.showAvailableLvls(manager.getCurrentLVL());
        }
        catch (CustomException e)
        {
            factory.createLogInWindow();
            factory.showMessage(e);
        }

    }

    public boolean isFinished() {
        return finished;
    }


    public void levelCompleted() {
        manager.update(currentLvl);
        resolver.showAvailableLvls(manager.getCurrentLVL());
    }

   public static Controller getController()
    {
        if(instance==null)
        {
            instance= new Controller();
        }
        return instance;
    }
}
