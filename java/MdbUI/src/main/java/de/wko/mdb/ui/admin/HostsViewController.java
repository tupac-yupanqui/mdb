package de.wko.mdb.ui.admin;

import de.wko.mdb.types.Host;
import de.wko.mdb.types.enums.EHostType;
import de.wko.mdb.ui.UIContext;
import de.wko.mdb.wrapper.HostWrapper;
import de.wko.mdb.wrapper.WrapperConnector;
import de.wko.mdb.wrapper.WrapperException;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
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
    TableColumn<Host, String> colName;
    @FXML
    TableColumn<Host, String> colAddress;
    @FXML
    TableColumn<Host, String> colType;

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
        PseudoClass selectedp = PseudoClass.getPseudoClass("selectedp");
        PseudoClass beides = PseudoClass.getPseudoClass("beides");

        hostsTable.setRowFactory(tv -> {
            TableRow<Host> row = new TableRow<>() {
                @Override
                protected void updateItem(Host item, boolean empty) {
                    if (item!=null) {
                        pseudoClassStateChanged(beides, item.getType().equals(EHostType.FTP)|| item.getType().equals(EHostType.HARDDRIVE));
                        pseudoClassStateChanged(available, item.getType().equals(EHostType.HARDDRIVE));
                        pseudoClassStateChanged(selectedp, item.getType().equals(EHostType.FTP));
                    }
                }
            };
            return row;
        });
    }

    public void loadView() {
        try {
            List<Host> hosts =  hostWrapper.getAllHosts();
            for (Host h : hosts) {
                hostsTable.getItems().add(h);
            }
        } catch (WrapperException e) {
            e.printStackTrace();
        }
    }
}
