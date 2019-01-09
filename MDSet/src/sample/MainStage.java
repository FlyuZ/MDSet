package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.net.URL;

public class MainStage extends Stage {
    BorderPane border = new BorderPane();
    VBox vbox = new VBox();
    HBox hbox = new HBox();
    WebView webView = new WebView();
    WebEngine webEngine = webView.getEngine();
    ScrollPane scrollPane = new ScrollPane();
    Button button = new Button();

    public void init() {
        scrollPane.setContent(webView);
        webEngine.getLoadWorker().stateProperty()
                .addListener(new ChangeListener<Worker.State>() {
                    @Override
                    public void changed(ObservableValue ov, Worker.State oldState, Worker.State newState) {
                        if (newState == Worker.State.SUCCEEDED) {
                            //stage.setTitle(webEngine.getLocation());
                        }
                    }
                });
        URL url = this.getClass().getResource("/echartsfroceDemo.html");
        webEngine.load(url.toString());

        hbox.setAlignment(Pos.CENTER);
        vbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(50); //设置内距
        vbox.setSpacing(10); //设置内距
        vbox.setPadding(new Insets(10, 10, 10, 10));//设置外距
        hbox.getChildren().addAll(webView, vbox);
        vbox.getChildren().addAll(button);

        HBox.setHgrow(webView, Priority.ALWAYS);
        HBox.setHgrow(vbox, Priority.ALWAYS);

        border.setCenter(hbox);
    }
    void initAction(){
        button.setOnAction((final ActionEvent e) -> {
            URL url = this.getClass().getResource("/hello.html");
            webEngine.load(url.toString());
        });
    }
    public MainStage() {
        init();
        initAction();
        setScene(new Scene(border, Color.web("#949494")));
        setResizable(true);
        show();
    }
}
