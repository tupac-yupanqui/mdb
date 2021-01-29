package de.wko.mdb.cli.cmd;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.validation.Validator;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@ShellComponent()
public class MyCommands {

    @ShellMethod("Add two integers together.")
    public int add(int a, int b) {
        return a+b;
    }
    @ShellMethod(value = "Test.", key = "echo all")
    public String echoAll(@TESTV String s) {
        return null;
    }
}
