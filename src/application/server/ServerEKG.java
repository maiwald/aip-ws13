package application.server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

class ServerEKG extends Thread {
    private final ServerFacade serverFacade;

    public ServerEKG(ServerFacade serverFacade) {
        this.serverFacade = serverFacade;
    }

    public void run() {
        try {
            while (this.serverFacade.running) {
                sendLifeSign(this.serverFacade.serverId);
                Thread.sleep(200);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendLifeSign(String serverId) throws IOException {
        Socket socket = new Socket(this.serverFacade.monitorHost, this.serverFacade.monitorPort);
        OutputStream out = socket.getOutputStream();
        out.write(serverId.getBytes());
        out.close();
        socket.close();
    }
}