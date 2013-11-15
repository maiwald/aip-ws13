package loadbalancer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Monitor extends Thread {
    private final int serverPort = 65001;

    private static List<Instance> instances = new ArrayList<Instance>();

    public static synchronized List<Instance> getInstances() {
        return instances;
    }

    public static synchronized boolean addInstance(Instance i) {
        return instances.add(i);
    }

    public Monitor() {

    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(this.serverPort);
            serverSocket.setReuseAddress(true);

            Socket socket;

            while (true) {
                socket = serverSocket.accept();
                handleRequest(socket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleRequest(Socket socket) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

}
