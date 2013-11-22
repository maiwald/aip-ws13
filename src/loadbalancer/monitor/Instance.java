package loadbalancer.monitor;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;

import application.server.ServerInstance;

public class Instance {

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

    public ServerInstance getStub() throws AccessException, RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry();
        return (ServerInstance) registry.lookup(this.id);
    }

}
