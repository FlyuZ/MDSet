package view;

import algorithm.MDSet;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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
    private FileChooser fileChooser = new FileChooser();
    private Thread mdsThread;
    private MDSet mdset;

    public void init() {
        selectFile.setText("Select File");
        run.setText("Calculate MDS");
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
        vbox.getChildren().addAll(selectFile, run);

        HBox.setHgrow(webView, Priority.ALWAYS);
        HBox.setHgrow(vbox, Priority.ALWAYS);

        border.setCenter(hbox);
    }

    private void initAction() {
        selectFile.setOnAction((final ActionEvent e) -> {
            File file = fileChooser.showOpenDialog(this);
            if(file != null){
                mdset = new MDSet(file);
                URL url = this.getClass().getResource("/before.html");
                webEngine.load(url.toString());
            }
        });
        run.setOnAction((final ActionEvent e) -> {
            //mdsThread = new Thread( () -> mdset.run() );
            //mdsThread.start();
            mdset.run();
            URL url = this.getClass().getResource("/after.html");
            webEngine.load(url.toString());
        });
    }

    MainStage() {
        init();
        initAction();
        Scene scene = new Scene(border);
        setScene(scene);
        setResizable(true);
        getIcons().add(new Image("/icon.png"));
//        setOnCloseRequest(new EventHandler<WindowEvent>() {
//            @Override
//            public void handle(WindowEvent event) {
//                mdsThread.interrupt();
//            }
//        });
        show();
    }
}
