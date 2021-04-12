package de.wko.mdb.ui.admin.entry;

import de.wko.mdb.types.Host;
import de.wko.mdb.types.enums.EHostType;

public class HostEntry {
    Host host;
    boolean available;

    public HostEntry(Host host, boolean available) {
        this.host = host;
        this.available = available;
    }

    public String getName() {
        return host.getName();
    }

    public String getAddress() {
        return host.getAddress();
    }

    public EHostType getType() {
        return host.getType();
    }

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

}
