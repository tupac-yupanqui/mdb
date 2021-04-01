package de.wko.mdb.ui;

import de.wko.mdb.ui.admin.MainView;
import de.wko.mdb.ui.admin.SettingsView;
import de.wko.mdb.ui.admin.StatusView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"de.wko.mdb"})
public class FxbootApplication extends Application {

    @Autowired
    MainView mainView;
    @Autowired
    StatusView statusView;
    @Autowired
    SettingsView settingsView;

    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void init() {
        SpringApplication application = new SpringApplication(getClass());
        application.setWebApplicationType(WebApplicationType.NONE);
        application.run().getAutowireCapableBeanFactory().autowireBean(this);
        //SpringApplication.run(getClass()).getAutowireCapableBeanFactory().autowireBean(this);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("null");
        primaryStage.initStyle(StageStyle.UNDECORATED);

        StackPane rootPane = new StackPane();
        rootPane.getStylesheets().add("css/main.css");
        rootPane.getStyleClass().add("splashPane");

        Image image = new Image("img/tuomas3.jpg");
        System.out.println(image.getUrl());
        ImageView imageView = new ImageView(image);

        /*
        label.setBorder(new Border(new BorderStroke(Color.GREEN,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
*/
        imageView.setFitWidth(600);
        imageView.setPreserveRatio(true);

        double factor = image.getWidth()/600;
        double height = image.getHeight()/factor;

        Label label = new Label("MyMusic");
        label.setTextFill(Color.web("#ffcc00"));
        String path = getClass().getResource("/fonts/mama.otf").toString();
        Font font = Font.loadFont(path, 28);
        label.setFont(font);
        label.setOpacity(1);

        Button exitbutton = new Button("X");
        exitbutton.getStyleClass().add("splashExitButton");
        exitbutton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Platform.exit();
            }
        });

        Button archiveButton = new Button("Archive");
        archiveButton.getStyleClass().add("splashButton");
        archiveButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                primaryStage.close();
               // ArchiveUI archiveUI = new ArchiveUI();
                System.out.println("DURCH");
            }
        });
        Button playerButton = new Button("Player");
        playerButton.getStyleClass().add("splashButton");
        playerButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            }
        });

        AnchorPane anchorPaneLabel = new AnchorPane();
        AnchorPane.setTopAnchor(label, 0.);
        AnchorPane.setLeftAnchor(label, 10.);
        AnchorPane.setTopAnchor(exitbutton, 0.);
        AnchorPane.setRightAnchor(exitbutton, 0.);
        AnchorPane.setTopAnchor(archiveButton, 80.);
        AnchorPane.setLeftAnchor(archiveButton, 10.);
        AnchorPane.setTopAnchor(playerButton, 95.);
        AnchorPane.setLeftAnchor(playerButton, 10.);
        anchorPaneLabel.getChildren().addAll(label, exitbutton, archiveButton, playerButton);

        Scene scene = new Scene(rootPane, 600, height);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("css/main.css").toExternalForm());

        rootPane.getChildren().addAll(imageView, anchorPaneLabel);

        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.close();

        mainView.show();

        mainView.setContent(settingsView.getRoot());


    }
}
