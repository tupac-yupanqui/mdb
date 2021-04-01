package de.wko.mdb.ui.common;

import de.wko.mdb.types.DbConnector;
import de.wko.mdb.types.RestConnector;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Properties;

public class RemoteRestConnector {

    private StringProperty host;
    private StringProperty port;
    private StringProperty user;
    private StringProperty password;

    public RemoteRestConnector() {
        host = new SimpleStringProperty("");
        port = new SimpleStringProperty("");
        user = new SimpleStringProperty("");
        password = new SimpleStringProperty("");
    }

    public RemoteRestConnector(RestConnector connector) {
        host = new SimpleStringProperty(connector.getHost());
        port = new SimpleStringProperty(connector.getPort());
        user = new SimpleStringProperty(connector.getUser());
        password = new SimpleStringProperty(connector.getPassword());
    }

    public RemoteRestConnector(Properties properties) {
        host = new SimpleStringProperty(properties.getProperty(PropertyNames.PROP_REST_NAME, ""));
        port = new SimpleStringProperty(properties.getProperty(PropertyNames.PROP_REST_PORT, ""));
        user = new SimpleStringProperty(properties.getProperty(PropertyNames.PROP_REST_USER, ""));
        password = new SimpleStringProperty(properties.getProperty(PropertyNames.PROP_REST_PASSWORD, ""));
    }

    public RestConnector getType() {
        RestConnector type = new RestConnector();
        type.setHost(host.get());
        type.setPort(port.get());
        type.setUser(user.get());
        type.setPassword(password.get());
        return type;
    }

    public void save(Properties properties) {
        properties.setProperty(PropertyNames.PROP_REST_NAME, host.get());
        properties.setProperty(PropertyNames.PROP_REST_PORT, port.get());
        properties.setProperty(PropertyNames.PROP_REST_USER, user.get());
        properties.setProperty(PropertyNames.PROP_REST_PASSWORD, password.get());
    }

    public String getHost() {
        return host.get();
    }

    public StringProperty hostProperty() {
        return host;
    }

    public void setHost(String host) {
        this.host.set(host);
    }

    public String getPort() {
        return port.get();
    }

    public StringProperty portProperty() {
        return port;
    }

    public void setPort(String port) {
        this.port.set(port);
    }

    public String getUser() {
        return user.get();
    }

    public StringProperty userProperty() {
        return user;
    }

    public void setUser(String user) {
        this.user.set(user);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }
}
