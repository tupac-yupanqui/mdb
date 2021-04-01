package de.wko.mdb.ui.admin;

import de.wko.mdb.ui.UIContext;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MainView {

    private final FxWeaver fxWeaver;

    @Autowired
    private UIContext context;

    @Autowired
    private MainViewController controller;

    @Autowired
    public MainView(FxWeaver fxWeaver) {
        this.fxWeaver = fxWeaver;
        fxWeaver.loadView(MainViewController.class);
    }

    @PostConstruct
    public void postConstruct() {
    }

    public void setContent(Node n) {
        controller.mainView.setCenter(n);
    }

    public Parent getRoot() {
        return controller.mainView;
    }

    public void show() {
        Stage stage = new Stage();
        stage.setScene(new Scene(controller.mainView));
        stage.show();

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                context.save();
            }
        });
    }
}
