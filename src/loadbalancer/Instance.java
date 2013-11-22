package loadbalancer;

import java.util.Date;

public class Instance {

    public static final int DEAD = -1;

    private final int id;
    private int status;
    private final Date lastConnected;

    public Instance(int id) {
        this.id = id;
        this.status = 1;
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
    
    public void setStatusDead(){
    	this.status = DEAD;
    }
    
    public String toString(){
    	return String.format("Instance: %d, Alive: %s", this.getId(), (this.status == DEAD ? "no" : "yes"));
    }
    
}
