package loadbalancer.monitor;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;

import application.server.ServerInstance;

public class Instance {

    public static final int DEAD = -1;
    public static final int ALIVE = 1;

    private final String id;
    private int status = ALIVE;

    private Date onlineSince = new Date();
    private Date offlineSince = null;
    private Date lastConnected = new Date();

    public Instance(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public int getStatus() {
        return status;
    }

    public long getMillisecondsSinceLastLifesign() {
        return ((new Date().getTime() - this.lastConnected.getTime()));
    }

    public long getSecondsOnline() {
        return ((new Date().getTime() - this.onlineSince.getTime()) / 1000);
    }

    public long getSecondsOffline() {
        return ((new Date().getTime() - this.offlineSince.getTime()) / 1000);
    }

    void updateLastConnected() {
        this.lastConnected = new Date();
    }

    void setAlive() {
        if (this.status == DEAD) {
            this.status = ALIVE;
            this.offlineSince = null;
            this.onlineSince = new Date();
        }
    }

    void setDead() {
        if (this.status == ALIVE) {
            this.status = DEAD;
            this.offlineSince = new Date();
            this.onlineSince = null;
        }
    }

    public String toString() {
        return String.format("Instance: %s, Alive: %s", this.getId(), (this.status == DEAD ? "no" : "yes"));
    }

    public void start() {
        try {
            getStub().start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        try {
            getStub().stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ServerInstance getStub() {
        ServerInstance instance = null;
        try {
            Registry registry = LocateRegistry.getRegistry();
            instance = (ServerInstance) registry.lookup(this.id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }

}
