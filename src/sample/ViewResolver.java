package sample;

import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.TreeMap;


/**
 * Created by MSI on 28.11.2015.
 */
public interface ViewResolver {
    public void start(Stage primaryStage);
    public void showAvailableLvls(int lvl);
    public void highlightError();
    public void deleteHilight();
    public void showLvl(String task, String instruction);
    public void showResults(TreeMap<Long,Character> timeTable,float CPS);
    public String getText();
}
