package de.wko.mdb.wrapper;

import org.springframework.stereotype.Component;

@Component
public class SecurityWrapper {

    public SecurityWrapper() {
        System.out.println("INITIALIZE");
    }

    public void printtest() {
        System.out.println("WRAPPER");
    }
}
