package loadbalancer;

public class Instance {

    public final int DEAD = -1;
    public final int ALIVE = 1;

    private final int id;
    private final int status;

    public Instance(int id, int status) {
        this.id = id;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getStatus() {
        return status;
    }
}
