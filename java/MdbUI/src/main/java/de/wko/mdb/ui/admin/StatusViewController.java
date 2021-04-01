package de.wko.mdb.ui.admin;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

@Component
@FxmlView("/fxml/admin/statusView.fxml")
public class StatusViewController {

    @FXML
    VBox statusView;
}
