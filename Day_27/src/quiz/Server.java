package quiz;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	public static void main(String[] args) throws Exception {
		ServerSocket server = new ServerSocket(8080);
		while(true) {
			Socket sock = server.accept();
			//ClientThread ct = new ClientThread(sock);
			//ct.start();
			ClientLogin cl = new ClientLogin(sock);
			cl.start();
		}
	}
	
}
