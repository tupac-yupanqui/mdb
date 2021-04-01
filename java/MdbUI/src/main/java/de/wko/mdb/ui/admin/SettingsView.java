package de.wko.mdb.ui.admin;

import de.wko.mdb.ui.UIContext;
import javafx.scene.Node;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class SettingsView {
    private final FxWeaver fxWeaver;

    @Autowired
    UIContext context;

    @Autowired
    private SettingsViewController controller;

    @Autowired
    public SettingsView(FxWeaver fxWeaver) {
        this.fxWeaver = fxWeaver;
    }

    @PostConstruct
    private void postConstruct() {
        fxWeaver.loadView(SettingsViewController.class, context.getStringResources());
    }

    public Node getRoot() {
        return controller.settingsView;
    }
}
