package loadbalancer.monitor;

import java.util.Date;

class Instance {

    public static final int DEAD = -1;

    private final String id;
    private int status;
    private final Date lastConnected;

    public Instance(String id) {
        this.id = id;
        this.status = 1;
        this.lastConnected = new Date();
    }

    public String getId() {
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

    void setStatusDead() {
        this.status = DEAD;
    }

    public String toString() {
        return String.format("Instance: %s, Alive: %s", this.getId(), (this.status == DEAD ? "no" : "yes"));
    }

}
