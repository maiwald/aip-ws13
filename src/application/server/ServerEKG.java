package application.server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

class ServerEKG extends Thread {
    private final String serverId;
    private final String monitorHost;
    private final int monitorPort;

    public ServerEKG(String serverId, String monitorHost, int monitorPort) {
        this.serverId = serverId;
        this.monitorHost = monitorHost;
        this.monitorPort = monitorPort;

    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                sendLifeSign(this.serverId);
                Thread.sleep(2 * 1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendLifeSign(String serverId) throws IOException {
        Socket socket = new Socket(this.monitorHost, this.monitorPort);
        OutputStream out = socket.getOutputStream();
        out.write(serverId.getBytes());
        out.close();
        socket.close();
    }
}