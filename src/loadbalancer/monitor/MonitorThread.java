package loadbalancer.monitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import loadbalancer.Instance;

class MonitorThread extends Thread {

	@Override
	public void run() {
		try {
			ServerSocket serverSocket = new ServerSocket(Monitor.SERVER_PORT);
			serverSocket.setReuseAddress(true);

			while (true) {
				Socket socket = serverSocket.accept();
				new RequestHandlerThread(socket).start();

			}

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
				BufferedReader inBuffer = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));

				while (true) {
					String input = inBuffer.readLine();
					if (input != null) {
						Instance i = new Instance(Integer.parseInt(input));
						Monitor.addInstance(i);
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}
}
