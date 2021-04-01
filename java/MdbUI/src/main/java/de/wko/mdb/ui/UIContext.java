 package de.wko.mdb.ui;

import de.wko.mdb.ui.common.LocalDbConnector;
import de.wko.mdb.ui.common.RemoteRestConnector;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

@Component
public class UIContext {

    private ResourceBundle stringResources;
    private Properties properties;

    public static final String MODE_UNKNOWN = "Unknown";
    public static final String MODE_LOCAL   = "Local";
    public static final String MODE_REMOTE  = "Remote";
    StringProperty mode;

    public static final String STATUS_CONNECTED   = "Connected";
    public static final String STATUS_UNCONNECTED = "Not connected";
    StringProperty status;

    private LocalDbConnector dbConnector;
    private RemoteRestConnector restConnector;

    private UIContext() {
    }

    @PostConstruct
    private void postConstruct() {
        Locale locale = new Locale("en", "UK");
        stringResources = ResourceBundle.getBundle("fxml/admin/strings", locale);
        load();
    }

    public String getMode() {
        if (mode!=null) {
            return mode.get();
        }
        return MODE_UNKNOWN;
    }

    public StringProperty modeProperty() {
        if (mode == null) {
            mode = new SimpleStringProperty(MODE_LOCAL);
        }
        return mode;
    }

    public void setMode(String mode) {
        this.modeProperty().set(mode);
    }

    public String getStatus() {
        if (status !=null) {
            return status.get();
        }
        return STATUS_UNCONNECTED;
    }

    public StringProperty statusProperty() {
        if (status == null) {
            status = new SimpleStringProperty(STATUS_UNCONNECTED);
        }
        return status;
    }

    public void setStatus(String status) {
        this.statusProperty().set(status);
    }

    public ResourceBundle getStringResources() {
        return stringResources;
    }

    public LocalDbConnector getDbConnector() {
        return dbConnector;
    }

    public void setDbConnector(LocalDbConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    public RemoteRestConnector getRestConnector() {
        return restConnector;
    }

    public void setRestConnector(RemoteRestConnector restConnector) {
        this.restConnector = restConnector;
    }

    public void load() {
        try {
            File propFile = new File("settings.properties");
            propFile.createNewFile();
            InputStream input = new FileInputStream(propFile);

            properties = new Properties();
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
            return;
        }
        String modeStr = properties.getProperty("mode", MODE_UNKNOWN);
        if (!modeStr.equals(MODE_UNKNOWN)) {
            modeProperty().setValue(modeStr);
        }

        dbConnector = new LocalDbConnector(properties);
        restConnector = new RemoteRestConnector(properties);
    }


    public void save() {
        try {
            dbConnector.save(properties);
            restConnector.save(properties);
            OutputStream output = new FileOutputStream("settings.properties");
            properties.store(output, null);
        } catch (IOException io) {
            io.printStackTrace();
            return;
        }
    }
}
