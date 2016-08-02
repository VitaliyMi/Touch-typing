package view.stages;

import exceptions.CustomException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.Controller;
import sample.ErrorChecker;
import sample.PerformanceAnalytics;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MSI on 18.02.2016.
 */


public class StageFactory {
    private Stage stage;
    private Scene scene;
    private HBox box;
    private Text taskText;
    private TextArea textArea;
    private FlowPane flowPane;
    private boolean isError;
    private String input;
    private String task;
    private VBox leftTextbox;
    static Controller controller= Controller.getController();


    public Stage createLevelScene( final String taskLocal, final String instruction)
    {
        task = taskLocal;
        stage = new Stage();
        box = new HBox(50);
        textArea = new TextArea();
        taskText = new Text(task);
        taskText.setWrappingWidth(600);
        flowPane = new FlowPane();
        flowPane.getChildren().add(taskText);
        leftTextbox = new VBox();
        final HBox rightInstructionBox = new HBox(20);
        final VBox instructions = new VBox();
        final Text instructionText = new Text(instruction);
        instructionText.setWrappingWidth(200);
        final Button close = createButtonWithRotatedText("Hide instruction");
        final Button show = createButtonWithRotatedText("Show instruction");

        leftTextbox.getChildren().addAll(flowPane,textArea );
        instructions.getChildren().add(instructionText);
        rightInstructionBox.getChildren().addAll(instructions, close);


        close.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                rightInstructionBox.getChildren().removeAll(instructions, close);
                rightInstructionBox.getChildren().add(show);
                stage.setHeight(500);
                stage.setWidth(700);

            }
        });

        show.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                rightInstructionBox.getChildren().remove(show);
                rightInstructionBox.getChildren().addAll(instructions, close);
                stage.setHeight(500);
                stage.setWidth(920);
            }
        });


        textArea.textProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1)
            {

                input=t1;
                if(t1.equals(task.substring(0,t1.length())))
                {
                    if(isError)
                    {
                        errorFixed();
                        upDateVeiw();
                    }
                    if(t1.equals(task))
                    {
                        String result = PerformanceAnalytics.stopClock(taskLocal);
                         controller.levelCompleted();

                        showResult(result);
                    }
                    else
                    {
                        PerformanceAnalytics.noteChar(t1);
                    }
                }
                else
                {
                    errorFound();
                    upDateVeiw();
                }
            }
        });

        box.getChildren().addAll(leftTextbox,rightInstructionBox);
        scene = new Scene(box,900, 500);
        System.out.println(scene);
        scene.getStylesheets().add(getResource("style.css"));
        stage.setScene(scene);
        PerformanceAnalytics.startClock();
        return stage;
    }


    private FlowPane highlightErrors(String text, List<Integer> errorIndexes)
    {
        int count =0;
        FlowPane pane = new FlowPane();
        List<String> words = new ArrayList<String>();
        words.add(text.substring(0, errorIndexes.get(0)));


         int i;
            for(i=1; i<errorIndexes.size(); i++)
            {
                if(errorIndexes.get(0)==0) count++;
                if(count%2!=1) words.add(text.substring(errorIndexes.get(i-1),errorIndexes.get(i)+1));
                else words.add(text.substring(errorIndexes.get(i-1),errorIndexes.get(i)));
                count++;

            }
            words.add(text.substring(errorIndexes.get(i-1)+1));

        count=0;
        for (String word:words)
        {
            Text tex = new Text(word);
            if(count%2==1)
            {
                tex.getStyleClass().add("error");
            }
            else {
                for(String w: word.split(" ")){
                    tex=new Text(w+" ");
                    tex.getStyleClass().add("plain");
                    pane.getChildren().addAll(tex);
                }
                tex= new Text("");
            }
                pane.getChildren().addAll(tex);

            count++;
        }

        return pane;
    }

    public void upDateVeiw()
    {
        if(isError){
            List<Integer> errorindexes= ErrorChecker.checkTheError(input, task);
            leftTextbox.getChildren().removeAll(textArea, flowPane);
            flowPane=highlightErrors(task,errorindexes);
            flowPane.setPrefWrapLength(600);
            leftTextbox.getChildren().addAll(flowPane, textArea);
        }

        else

        {
            leftTextbox.getChildren().removeAll(flowPane, textArea);
            flowPane= new FlowPane();
            flowPane.setPrefWrapLength(600);
            taskText.getStyleClass().add("plain");
            flowPane.getChildren().addAll(taskText);
            leftTextbox.getChildren().addAll(flowPane, textArea);
        }
    }

    private String getResource(String resourceName) {
        return getClass().getResource(resourceName).toExternalForm();
    }

    private void errorFound()
    {
        isError=true;
    }

    private void errorFixed()
    {
        isError=false;
    }

    public void showResult(String result)
    {
        Label l = new Label(result);
        VBox b = new VBox();
        b.getChildren().add(l);
        Scene resultScene = new Scene(b,300,300);
        Stage st = new Stage();
        st.setScene(resultScene);
        st.show();
    }


    public void createLogInWindow()
    {
        final Stage primaryStage = new Stage();
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


    public void showMessage(CustomException e)
    {
        String message = e.getMessage();
        Stage exceptionStage= new Stage();
        Label t = new Label(message);
        VBox box = new VBox();
        box.getChildren().addAll(t);
        Scene exceptionScene = new Scene(box, 200, 300);
        exceptionStage.setScene(exceptionScene);
        exceptionStage.show();
    }
    private Button createButtonWithRotatedText(String text) {
        Button button = new Button();
        Label label = new Label(text);
        label.setRotate(-90);
        button.setGraphic(new Group(label));
        return button;
    }

}