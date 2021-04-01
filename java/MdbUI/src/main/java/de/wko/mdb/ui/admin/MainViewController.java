package de.wko.mdb.ui.admin;

import de.wko.mdb.ui.UIContext;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@FxmlView("/fxml/admin/mainView.fxml")
public class MainViewController implements Initializable  {

    @Autowired
    UIContext context;
    @Autowired
    SettingsView settingsView;
    @Autowired
    StatusView statusView;

    @FXML
    BorderPane mainView;
    @FXML
    Label footerMode;
    @FXML
    Label footerStatus;

    public MainViewController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        footerMode.setText("Mode: "+context.getMode());
        context.modeProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                footerMode.setText("Mode: "+t1);
            }
        });
        context.statusProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                footerStatus.setText("Status: "+t1);
            }
        });
    }

    @FXML
    protected void menuStatusAction(ActionEvent event) {
        mainView.setCenter(statusView.getRoot());
    }

    @FXML
    protected void menuSettingsAction(ActionEvent event) {
        mainView.setCenter(settingsView.getRoot());
    }

    @FXML
    protected void menuTestAction(ActionEvent event) {
        context.setMode(UIContext.MODE_REMOTE);
        context.setStatus(UIContext.STATUS_CONNECTED);
    }

    @FXML
    protected void menuExitAction() {
        Stage stage = (Stage)mainView.getScene().getWindow();
        stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    public void setContentArea(Node node) {
        mainView.setCenter(node);
    }

}
