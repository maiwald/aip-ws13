package loadbalancer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Monitor extends Thread {
    private static final int SERVER_PORT = 65001;

    private static Map<Integer, Instance> instances = new HashMap<Integer, Instance>();

    public static synchronized List<Instance> getInstances() {
        List<Instance> result = new ArrayList<Instance>();
        result.addAll(instances.values());
        return result;
    }

    public static synchronized void addInstance(Instance instance) {
        instances.put(instance.getId(), instance);
    }

    public Monitor() {
        new MonitorThread().start();
    }

    class MonitorThread extends Thread {

        @Override
        public void run() {
            try {
                ServerSocket serverSocket = new ServerSocket(Monitor.SERVER_PORT);
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
            String input = new BufferedReader(new InputStreamReader(socket.getInputStream())).readLine();
            Instance i = new Instance(Integer.parseInt(input), 1);
            Monitor.addInstance(i);
        }

    }
}
