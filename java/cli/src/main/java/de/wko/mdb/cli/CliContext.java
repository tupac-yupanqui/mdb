package de.wko.mdb.cli;

import de.wko.mdb.fs.AbstractFileSystem;
import de.wko.mdb.types.AuthData;
import de.wko.mdb.types.request.LoginRequest;
import org.apache.logging.log4j.util.Strings;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class CliContext {

    private Settings settings;
    private AuthData authData = null;
    private LoginRequest loginRequest = null;

    private AbstractFileSystem currentFileSystem = null;
    private AbstractFileSystem localFileSystem = null;

    public CliContext() {
        settings = new Settings();
        String username = settings.getProperty(Settings.KEY_LOGIN_USERNAME);
        String password = settings.getProperty(Settings.KEY_LOGIN_PASSWORD);
        if (Strings.isNotEmpty(username) && Strings.isNotEmpty(password)) {
            loginRequest = new LoginRequest(username, password);
        }
    }

    public void saveSettings() {
        settings.store();
    }

    public void setAuthData(AuthData data) {
        authData = data;
    }

    public AuthData getAuthData() {
        return authData;
    }

    public boolean isLoggedIn() {
        return authData!=null;
    }

    public LoginRequest getLoginRequest() {
        return loginRequest;
    }

    public void setLoginRequest(LoginRequest loginRequest) {
        this.loginRequest = loginRequest;
    }

    public void storeLoginRequest() {
        settings.setProperty(Settings.KEY_LOGIN_USERNAME, loginRequest.getUsername());
        settings.setProperty(Settings.KEY_LOGIN_PASSWORD, loginRequest.getPassword());
        settings.store();
    }

    public void clearLoginRequest() {
        settings.unsetProperty(Settings.KEY_LOGIN_USERNAME);
        settings.unsetProperty(Settings.KEY_LOGIN_PASSWORD);
        settings.store();
    }

    public void setCurrentFileSystem(AbstractFileSystem fs) {
        currentFileSystem = fs;
    }

    public AbstractFileSystem getCurrentFileSystem() {
        return currentFileSystem;
    }

    public AbstractFileSystem getLocalFileSystem() {
        return localFileSystem;
    }

    public void setLocalFileSystem(AbstractFileSystem localFileSystem) {
        this.localFileSystem = localFileSystem;
    }
}
