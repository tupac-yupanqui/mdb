package de.wko.mdb.ui.admin;

import de.wko.mdb.ui.UIContext;
import de.wko.mdb.wrapper.HostWrapper;
import javafx.scene.Node;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class HostsView {
    private final FxWeaver fxWeaver;

    @Autowired
    UIContext context;

    @Autowired
    private HostsViewController controller;

    @Autowired
    HostWrapper hostWrapper;

    @Autowired
    public HostsView(FxWeaver fxWeaver) {
        this.fxWeaver = fxWeaver;
    }

    @PostConstruct
    private void postConstruct() {
        fxWeaver.loadView(HostsViewController.class, context.getStringResources());
        System.out.println("POSTCONSTRUCT");
    }

    public Node getRoot() {
        return controller.hostsView;
    }

    public Node initView() {
        controller.loadView();
        return controller.hostsView;
    }
}
