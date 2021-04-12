package de.wko.mdb.ui.admin;

import de.wko.mdb.fs.AvailabiltyCheck;
import de.wko.mdb.types.Host;
import de.wko.mdb.ui.UIContext;
import de.wko.mdb.ui.admin.entry.HostEntry;
import de.wko.mdb.wrapper.HostWrapper;
import de.wko.mdb.wrapper.WrapperConnector;
import de.wko.mdb.wrapper.WrapperException;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.css.PseudoClass;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
@FxmlView("/fxml/admin/hostsView.fxml")
public class HostsViewController implements Initializable {
    @Autowired
    WrapperConnector wrapper;

    @Autowired
    UIContext context;

    @Autowired
    HostWrapper hostWrapper;

    @FXML
    VBox hostsView;

    @FXML
    ScrollPane hostsScrollPane;
    @FXML
    TableView hostsTable;
    @FXML
    TableColumn<HostEntry, String> colName;
    @FXML
    TableColumn<HostEntry, String> colAddress;
    @FXML
    TableColumn<HostEntry, String> colType;
    @FXML
    CheckBox showAvailableOnly;

    private List<HostEntry> entries = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hostsView.getStylesheets().add("/css/table.css");

        hostsTable.prefWidthProperty().bind(hostsScrollPane.widthProperty());
        hostsTable.prefHeightProperty().bind(hostsScrollPane.heightProperty());
        //        hostsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        colName.prefWidthProperty().bind(hostsTable.widthProperty().divide(3)); // w * 1/4
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.prefWidthProperty().bind(hostsTable.widthProperty().divide(3)); // w * 1/2
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colType.prefWidthProperty().bind(hostsTable.widthProperty().divide(4)); // w * 1/4
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));

        PseudoClass available = PseudoClass.getPseudoClass("available");

        hostsTable.setRowFactory(table -> new TableRow<HostEntry>() {
            @Override
            protected void updateItem(HostEntry item, boolean empty) {
                super.updateItem(item, empty);
                if (item!=null) {
                    pseudoClassStateChanged(available, item.isAvailable());
                }
            }
        });
    }

    @FXML
    public void refreshHostView() {
        loadView();
    }

    @FXML
    public void changeShowAvailable() {
        loadView();
    }

    public Stage progressWindow() {
        Stage windowStage = new Stage();
       // windowStage.initModality(Modality.APPLICATION_MODAL);
        windowStage.initStyle(StageStyle.UNIFIED);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/progressWindow.fxml"));
        try {
            Parent parent = loader.load();
            windowStage.setScene(new Scene(parent));
            windowStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return windowStage;
    }

    public Alert progressWindow2() {
        Alert myalert = new Alert(Alert.AlertType.CONFIRMATION, "WAIT");
        myalert.show();
        return myalert;
    }

    public void loadView() {

        Alert progress = progressWindow2();

        MyTask task = new MyTask();
        task.addEventFilter(WorkerStateEvent.WORKER_STATE_SUCCEEDED, new EventHandler() {
            @Override
            public void handle(Event event) {
                System.out.println("CLOSE");
                progress.close();
            }
        });

        new Thread(task).start();
    }

    private boolean checkHostAvailability(Host host) {
        if (host==null) return false;
        return AvailabiltyCheck.isHostAvailable(host);
    }

    private class MyTask extends Task {

        @Override
        protected Object call() throws Exception {
            try {
                List<Host> hosts = hostWrapper.getAllHosts();
                hostsTable.getItems().clear();
                for (Host h : hosts) {
                    HostEntry entry = new HostEntry(h, checkHostAvailability(h));
                    if (entry.isAvailable() || showAvailableOnly.isSelected() == false) {
                        hostsTable.getItems().add(entry);
                    }
                }
            } catch (WrapperException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
