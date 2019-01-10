package view;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        //setUserAgentStylesheet(STYLESHEET_MODENA);
        new MainStage();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
