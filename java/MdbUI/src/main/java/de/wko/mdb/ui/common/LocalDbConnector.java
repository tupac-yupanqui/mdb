package de.wko.mdb.ui.common;

import de.wko.mdb.types.DbConnector;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Properties;

public class LocalDbConnector {

    private StringProperty host;
    private StringProperty port;
    private StringProperty schema;
    private StringProperty user;
    private StringProperty password;

    public LocalDbConnector() {
        host = new SimpleStringProperty("");
        port = new SimpleStringProperty("");
        schema = new SimpleStringProperty("");
        user = new SimpleStringProperty("");
        password = new SimpleStringProperty("");
    }

    public LocalDbConnector(DbConnector connector) {
        host = new SimpleStringProperty(connector.getHost());
        port = new SimpleStringProperty(connector.getPort());
        schema = new SimpleStringProperty(connector.getSchema());
        user = new SimpleStringProperty(connector.getUser());
        password = new SimpleStringProperty(connector.getPassword());
    }

    public LocalDbConnector(Properties properties) {
        host = new SimpleStringProperty(properties.getProperty(PropertyNames.PROP_DB_NAME, ""));
        port = new SimpleStringProperty(properties.getProperty(PropertyNames.PROP_DB_PORT, ""));
        schema = new SimpleStringProperty(properties.getProperty(PropertyNames.PROP_DB_SCHEMA, ""));
        user = new SimpleStringProperty(properties.getProperty(PropertyNames.PROP_DB_USER, ""));
        password = new SimpleStringProperty(properties.getProperty(PropertyNames.PROP_DB_USER, ""));
    }

    public DbConnector getType() {
        DbConnector type = new DbConnector();
        type.setHost(host.get());
        type.setPort(port.get());
        type.setSchema(schema.get());
        type.setUser(user.get());
        type.setPassword(password.get());
        return type;
    }

    public void save(Properties properties) {
        properties.setProperty(PropertyNames.PROP_DB_NAME, host.get());
        properties.setProperty(PropertyNames.PROP_DB_PORT, port.get());
        properties.setProperty(PropertyNames.PROP_DB_SCHEMA, schema.get());
        properties.setProperty(PropertyNames.PROP_DB_USER, user.get());
        properties.setProperty(PropertyNames.PROP_DB_PASSWORD, password.get());
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

    public String getSchema() {
        return schema.get();
    }

    public StringProperty schemaProperty() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema.set(schema);
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
