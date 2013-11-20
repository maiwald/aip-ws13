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
    private static final int INSTANCE_CLEANUP_DELAY = 3;
    private static final int INSTANCE_LIFETIME = 10;

    private static Map<Integer, Instance> instances = new HashMap<Integer, Instance>();

    public static synchronized List<Instance> getInstances() {
        List<Instance> result = new ArrayList<Instance>();
        result.addAll(instances.values());
        return result;
    }

    public static synchronized void addInstance(Instance instance) {
        instances.put(instance.getId(), instance);
    }

    private static synchronized void removeInstance(Instance instance) {
        instances.remove(instance.getId());
    }

    public static void main(String[] args) {
        new Monitor();
    }

    public Monitor() {
        new MonitorThread().start();
        new CleanUpThread().start();
    }

    class CleanUpThread extends Thread {
        public void run() {
            try {
                while (true) {
                    Thread.sleep(Monitor.INSTANCE_CLEANUP_DELAY * 1000);
                    cleanDeadInstances();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        private void cleanDeadInstances() {
            for (Instance elem : getDeadInstances()) {
                Monitor.removeInstance(elem);
            }
        }

        private List<Instance> getDeadInstances() {
            List<Instance> result = new ArrayList<Instance>();
            for (Instance elem : Monitor.getInstances()) {
                if (elem.getLifeTimeInSeconds() > Monitor.INSTANCE_LIFETIME)
                    result.add(elem);
            }
            return result;
        }
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
