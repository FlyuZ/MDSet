package view;

import algorithm.MDSet;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class MainStage extends Stage {
    private BorderPane border = new BorderPane();
    private VBox vbox = new VBox();
    private HBox hbox = new HBox();
    private WebView webView = new WebView();
    private WebEngine webEngine = webView.getEngine();
    private ScrollPane scrollPane = new ScrollPane();
    private Button selectFile = new Button();
    private Button run = new Button();
    private Button show = new Button();
    private FileChooser fileChooser = new FileChooser();
    private Thread mdsThread;
    private MDSet mdset;

    public void init() {
        selectFile.setText("Select File");
        run.setText("Calculate MDS");
        show.setText("Show Result");
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
        URL url = this.getClass().getResource("/hello.html");
        webEngine.load(url.toString());

        hbox.setAlignment(Pos.CENTER);
        vbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10); //设置内距
        vbox.setSpacing(50); //设置内距
        vbox.setPadding(new Insets(10, 10, 10, 10));//设置外距
        hbox.getChildren().addAll(webView, vbox);
        vbox.getChildren().addAll(selectFile, run, show);

        HBox.setHgrow(webView, Priority.ALWAYS);
        HBox.setHgrow(vbox, Priority.ALWAYS);

        border.setCenter(hbox);
    }

    private void initAction() {
        selectFile.setOnAction((final ActionEvent e) -> {
            File file = fileChooser.showOpenDialog(this);
            mdsThread = new Thread() {
                public void run() {
                    System.out.println("线程启动");
                    mdset = new MDSet(file);
                    mdset.run();
                }
            };
            mdset = new MDSet(file);
        });
        run.setOnAction((final ActionEvent e) -> {
            mdsThread.start();
        });
        show.setOnAction((final ActionEvent e) -> {
            URL url = this.getClass().getResource("/jsonFroce.html");
            System.out.println(url.toString());
            webEngine.load(url.toString());
            System.out.println("运行完成");
        });
    }

    MainStage() {
        init();
        initAction();
        Scene scene = new Scene(border, Color.web("#cccccc"));
        //scene.getStylesheets().add();
        setScene(scene);
        setResizable(true);
        show();
    }
}
