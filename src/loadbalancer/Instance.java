package loadbalancer;

import java.util.Date;

public class Instance {

    public static final int DEAD = -1;

    private final int id;
    private final int status;
    private final Date lastConnected;

    public Instance(int id, int status) {
        this.id = id;
        this.status = status;
        this.lastConnected = new Date();
    }

    public int getId() {
        return id;
    }

    public int getStatus() {
        return status;
    }

    public long getLifeTimeInSeconds() {
        return ((new Date().getTime() - this.getLastConnected().getTime()) / 1000);
    }

    public Date getLastConnected() {
        return lastConnected;
    }
}
