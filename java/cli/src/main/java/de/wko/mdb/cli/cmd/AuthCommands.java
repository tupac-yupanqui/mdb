package de.wko.mdb.cli.cmd;

import de.wko.mdb.cli.CliContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.validation.Validator;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@ShellComponent()
public class AuthCommands {

    @Autowired
    CliContext context;

    @ShellMethod("Add two integers together.")
    public int add(int a, int b) {
        return a+b;
    }
    @ShellMethod(value = "Test.", key = "echo all")
    public String echoAll(@TESTV String s) {
        return null;
    }

    @ShellMethod(value="Store login data",key="save login")
    public void storeLoginData() {
        context.storeLoginRequest();
    }
    @ShellMethod(value="Clear login data",key="clear login")
    public void clearLoginData() {
        context.clearLoginRequest();
    }
}
