package application.server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import loadbalancer.monitor.Monitor;

public class ServerEKG {

	public static void sendLifeSign() throws IOException {
		Socket socket = new Socket("localhost", Monitor.SERVER_PORT);
		OutputStream out = socket.getOutputStream();
		String payload = "35";
		out.write(payload.getBytes());
		out.close();
		socket.close();
	}
}
