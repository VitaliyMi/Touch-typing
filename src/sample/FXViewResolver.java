package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


/**
 * Created by MSI on 28.11.2015.
 */
public class FXViewResolver implements ViewResolver {
    int lvlAvailable;
    private TextArea textArea;
    private String text;
    private int state;

    static Controller controller=Controller.getController();

    @Override
    public void start(Stage primaryStage) {
        showLogInWindow(primaryStage);
       /* primaryStage.setTitle("Blind typing");
        Group root = new Group();
        Label label1 = new Label("Welcome to FASTxTEN trainer");
        Label label2 = new Label("Select your level");
        Button selectLevel = new Button("Select level");
        selectLevel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showAvailableLvls(new Stage());
            }
        });
        VBox box = new VBox(25);

        box.getChildren().addAll(label1, label2, selectLevel);
        root.getChildren().addAll(box);
        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.show();*/
    }


    //Displays level with instruction and task
    @Override
    public void showLvl( String task, String instruction) {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Blind typing");
        Group root = new Group();
        textArea = new TextArea();
        text = task;
        textArea.setWrapText(true);
        Label ta = new Label(task);
        ta.setPrefWidth(500);


        Label inst = new Label(instruction);
        inst.setPrefWidth(500);
        inst.setWrapText(true);
        ta.setWrapText(true);
        VBox vBox = new VBox();
        vBox.getChildren().add(inst);
        vBox.getChildren().add(ta);
        vBox.getChildren().addAll(textArea);

        root.getChildren().add(vBox);
        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.show();


    }

//Displays levels that are available for this user
    @Override
    public void showAvailableLvls(int lvl) {
        final Stage stage= new Stage();
        stage.setTitle("Select lvl");
        VBox box = new VBox(10);
        for (int i = 1; i <= lvl; i++) {

            final int level = i;
            Button b = new Button("Level "+level);
            b.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                controller.showLvl(level);
                    stage.close();
                }
            });
            box.getChildren().add(b);

        }
        stage.setScene(new Scene(box, 500, 900));
        stage.show();


    }

    @Override
    public void highlightError() {
        textArea.setStyle("-fx-background-color: red;");
    }

    @Override
    public void deleteHilight() {
        textArea.setStyle("-fx-background-color: white;");

    }

    @Override
    public void showResults(TreeMap<Long,Character> timeTable, float CPM ) {
        Stage stage= new Stage();
        Label label= new Label();
        Label label2= new Label();
        for( Map.Entry<Long, Character> p : timeTable.entrySet())
        {
       //     System.out.println(p.getKey()+" nano "+p.getValue()+" "+(String.format("%.03f",Float.parseFloat(Long.toString(p.getKey()))/1000000000))+"sec");
        }
        Map.Entry<Long, Character> pair = timeTable.pollLastEntry();
        String print="";
        if(pair.getValue().equals(' ')){
            print= "space took "+(String.format("%.03f",Float.parseFloat(Long.toString(pair.getKey()))/1000000000))+" seconds";
        }
        else {
            print= pair.getValue()+" took "+(String.format("%.03f",Float.parseFloat(Long.toString(pair.getKey()))/1000000000))+" seconds";
        }
        label.setText(print);
        label2.setText("Your effective clicks per minute: "+CPM);
        VBox box = new VBox(10);
        label.setAlignment(Pos.CENTER);
        label2.setAlignment(Pos.CENTER);
        box.getChildren().addAll(label,label2);
        stage.setScene(new Scene(box, 300, 500));
        stage.show();
    }

    public void  showLogInWindow(final Stage primaryStage)
    {
        primaryStage.setTitle("Hello");
        VBox vBox = new VBox();

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label userName = new Label("User Name:");
        grid.add(userName, 0, 1);

        final TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);

        final PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);
        Button button1 = new Button("Sing in");
        Button button2 = new Button("Log in");

        grid.add(button1,0,4);
        grid.add(button2,1,4);
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
           controller.signIn(userTextField.getText(), pwBox.getText());
                primaryStage.close();
            }
        });

        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                controller.logIn(userTextField.getText(), pwBox.getText());
                primaryStage.close();
            }
        });
        primaryStage.setScene(new Scene(grid,400,300));
        primaryStage.show();
    }



    public TextArea getObservedArea()
    {
        return textArea;
    }

    public String getText() {
        return textArea.getText();
    }
}
