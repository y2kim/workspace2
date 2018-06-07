package quiz;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.net.Socket;

import javax.swing.JOptionPane;

public class ClientLogin extends Thread {
	private Socket sock;
	private String id;
	private String password;
	File file = new File("members.txt");
	BufferedReader bf;
	public ClientLogin(Socket sock) {
		this.sock = sock;
	}
	
	@Override
	public void run() {
		try(DataInputStream dis  = new DataInputStream(sock.getInputStream());
			DataOutputStream dos = new DataOutputStream(sock.getOutputStream());) {
			bf= new BufferedReader(new FileReader(file));
			id       = dis.readUTF();
			password = dis.readUTF();
			String str = null;
			boolean idcek= false;
			while((str=bf.readLine())!=null) {
				if(str.split("=")[1].split(":")[0].equals(id)&&
						str.split(":")[1].split("=")[1].equals(password)) {
					idcek = true;
					break;
				}else {
					idcek = false;
				}
			}
			if(idcek == true) {
				dos.writeInt(1);
			}else {
				dos.writeInt(2);
			}
			dos.flush();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
