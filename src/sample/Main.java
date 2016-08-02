package sample;

import javafx.application.Application;

import javafx.stage.Stage;



public class Main extends Application {
    Controller controller=Controller.getController();
    @Override
    public void start(Stage primaryStage) throws InterruptedException {
        System.out.println(controller.hashCode()+" in main");
        controller.start(primaryStage);
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}


