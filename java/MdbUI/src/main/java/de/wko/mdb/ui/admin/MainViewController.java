package de.wko.mdb.ui.admin;

import de.wko.mdb.types.Host;
import de.wko.mdb.ui.UIContext;
import de.wko.mdb.ui.dialog.MessageBox;
import de.wko.mdb.wrapper.HostWrapper;
import de.wko.mdb.wrapper.WrapperException;
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
import java.util.List;
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
    @Autowired
    HostsView hostsView;

    @Autowired
    HostWrapper hostWrapper;

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
    protected void menuHostsAction(ActionEvent event) {
        mainView.setCenter(hostsView.getRoot());
    }

    @FXML
    protected void menuTestAction(ActionEvent event) {
        try {
            List<Host> hosts = hostWrapper.getAllHosts();
            for (Host h : hosts) {
                System.out.println(String.format("####### %s (%s)", h.getName(), h.getType()));
            }
        } catch (WrapperException e) {
            MessageBox.alert2();
        }
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
