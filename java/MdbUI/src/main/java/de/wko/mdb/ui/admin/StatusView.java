package de.wko.mdb.ui.admin;

import javafx.scene.Node;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StatusView {
    private final FxWeaver fxWeaver;

    @Autowired
    private StatusViewController controller;

    @Autowired
    public StatusView(FxWeaver fxWeaver) {
        this.fxWeaver = fxWeaver;
        fxWeaver.loadView(StatusViewController.class);
    }

    public Node getRoot() {
        return controller.statusView;
    }
}
