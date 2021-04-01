package de.wko.mdb.ui.admin;

import de.wko.mdb.ui.UIContext;
import de.wko.mdb.wrapper.SecurityWrapper;
import de.wko.mdb.wrapper.WrapperConnector;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@FxmlView("/fxml/admin/settingsView.fxml")
public class SettingsViewController implements Initializable {

    @Autowired
    WrapperConnector wrapper;

    @Autowired
    UIContext context;

    @FXML
    VBox settingsView;

    @FXML
    ScrollPane propertyScrollPane;
    @FXML
    TitledPane modePropertyPane;
    @FXML
    TitledPane dbPropertyPane;
    @FXML
    TitledPane restPropertyPane;
    @FXML
    TitledPane nextPropertyPane;
    @FXML
    AnchorPane dbPropertyContentPane;

    @FXML
    ToggleGroup modeToggleGroup;
    @FXML
    RadioButton modeLocalButton;
    @FXML
    RadioButton modeRemoteButton;

    @FXML
    TextField tfDbHost;
    @FXML
    TextField tfDbPort;
    @FXML
    TextField tfDbSchema;
    @FXML
    TextField tfDbUser;
    @FXML
    TextField tfDbPassword;

    @FXML
    TextField tfRestHost;
    @FXML
    TextField tfRestPort;
    @FXML
    TextField tfRestUser;
    @FXML
    TextField tfRestPassword;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modePropertyPane.prefWidthProperty().bind(propertyScrollPane.widthProperty());
        dbPropertyPane.prefWidthProperty().bind(propertyScrollPane.widthProperty());
        dbPropertyPane.setExpanded(false);
        restPropertyPane.prefWidthProperty().bind(propertyScrollPane.widthProperty());
        restPropertyPane.setExpanded(false);
        nextPropertyPane.prefWidthProperty().bind(propertyScrollPane.widthProperty());
        nextPropertyPane.setExpanded(false);

        if (context.getMode().equals(UIContext.MODE_LOCAL)) {
            modeLocalButton.setSelected(true);
        }
        if (context.getMode().equals(UIContext.MODE_REMOTE)) {
            modeRemoteButton.setSelected(true);
        }
        modeToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                if (t1.equals(modeLocalButton)) {
                    context.setMode(UIContext.MODE_LOCAL);
                }
                if (t1.equals(modeRemoteButton)) {
                    context.setMode(UIContext.MODE_REMOTE);
                }
            }
        });

        tfDbHost.textProperty().bindBidirectional(context.getDbConnector().hostProperty());
        tfDbPort.textProperty().bindBidirectional(context.getDbConnector().portProperty());
        tfDbSchema.textProperty().bindBidirectional(context.getDbConnector().schemaProperty());
        tfDbUser.textProperty().bindBidirectional(context.getDbConnector().userProperty());
        tfDbPassword.textProperty().bindBidirectional(context.getDbConnector().passwordProperty());

        tfRestHost.textProperty().bindBidirectional(context.getRestConnector().hostProperty());
        tfRestPort.textProperty().bindBidirectional(context.getRestConnector().portProperty());
        tfRestUser.textProperty().bindBidirectional(context.getRestConnector().userProperty());
        tfRestPassword.textProperty().bindBidirectional(context.getRestConnector().passwordProperty());

    }


    @FXML
    public void actionConnectDb() {
        System.out.println("Connect DB");
    }

    @FXML
    public void actionConnectRest() {
        System.out.println("Connect REST");
        boolean result = wrapper.connect(context.getRestConnector().getType());
        System.out.println("Result: "+result);

    }
}
