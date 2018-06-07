import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.JFrame;

public class Clientsvote extends JFrame {

	public void callme() {
		
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Socket sock = new Socket("192.168.20.3",60000);
		DataOutputStream dos = new DataOutputStream(sock.getOutputStream());
		DataInputStream dis = new DataInputStream(sock.getInputStream());
		
		for(int i=0;i<3;i++) {
			dos.writeUTF("김찬연 : 2조 Pc방 관리 프로그램 ");
			//dos.writeInt(2);
			dos.flush();
			Thread.sleep(1000);
		}
	}

}
