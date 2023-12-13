package chatting;
import java.io.*;
import java.util.*;
import java.net.*;

public class Server {

	static ArrayList<ServerThread> list = new ArrayList<>();

	static int clientCount = 0;

	public static void main(String[] args) throws IOException {
		ServerSocket ssocket = new ServerSocket(5001);

		Socket s;

		while (true) {
			s = ssocket.accept();

			DataInputStream is = new DataInputStream(s.getInputStream());
			DataOutputStream os = new DataOutputStream(s.getOutputStream());

			ServerThread thread = new ServerThread(s, "client " + clientCount, is, os);
			list.add(thread);
			thread.start();
			clientCount++;

		}
	}
}

class ServerThread extends Thread {
	Scanner scn = new Scanner(System.in);
	private String name;
	final DataInputStream is;
	final DataOutputStream os;
	Socket s;
	boolean active;

	public ServerThread(Socket s, String name, DataInputStream is, DataOutputStream os) {
		this.is = is;
		this.os = os;
		this.name = name;
		this.s = s;
		this.active = true;
	}

	@Override
	public void run() {
		String message;
		while (true) {
			try {
				message = is.readUTF();
				System.out.println(message);
				if (message.equals("logout")) {
					this.active = false;
					this.s.close();
					break;
				}
				for (ServerThread t : Server.list) {
					t.os.writeUTF(this.name + " : " + message);
				}
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
		}
		try {
			this.is.close();
			this.os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
