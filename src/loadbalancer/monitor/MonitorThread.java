package loadbalancer.monitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

class MonitorThread extends Thread {

    private boolean terminated = false;

    public void terminate() {
        this.terminated = true;
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(Monitor.SERVER_PORT);
            serverSocket.setReuseAddress(true);

            while (!this.terminated) {
                Socket socket = serverSocket.accept();
                new RequestHandlerThread(socket).start();
            }

            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class RequestHandlerThread extends Thread {
        private final Socket socket;

        public RequestHandlerThread(Socket socket) {
            this.socket = socket;
        }

        public void run() {

            try {
                BufferedReader inBuffer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String input = inBuffer.readLine();
                Instance i = new Instance(input);
                Monitor.addInstance(i);

                this.socket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
