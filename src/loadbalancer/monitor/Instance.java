package loadbalancer.monitor;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;

import application.server.ServerInstance;

public class Instance {

    private final String id;
    private Date lastConnected = new Date();

    private Date onlineSince = new Date();
    private Date offlineSince = null;

    public Instance(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public boolean isAlive() {
        return onlineSince != null && offlineSince == null;
    }
    
    public boolean isDead() {
        return !isAlive();
    }

    public long getMillisecondsSinceLastLifesign() {
        return ((new Date().getTime() - this.lastConnected.getTime()));
    }

    public long getSecondsOnline() {
        if (this.onlineSince == null) return -1;
        return ((new Date().getTime() - this.onlineSince.getTime()) / 1000);
    }

    public long getSecondsOffline() {
        if (this.offlineSince == null) return -1;
        return ((new Date().getTime() - this.offlineSince.getTime()) / 1000);
    }

    void updateLastConnected() {
        this.lastConnected = new Date();
    }

    void setAlive() {
        if (this.isDead()) {
            this.offlineSince = null;
            this.onlineSince = new Date();
        }
    }

    void setDead() {
        if (this.isAlive()) {
            this.offlineSince = new Date();
            this.onlineSince = null;
        }
    }

    public String toString() {
        return String.format("Instance: %s, Alive: %s", this.getId(), (this.isAlive() ? "yes" : "no"));
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
