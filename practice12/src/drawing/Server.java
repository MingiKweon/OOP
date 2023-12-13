package drawing;

import java.io.*;
import java.net.*;
import java.util.*;

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
        try {
            while (true) {
                message = is.readUTF();
                System.out.println(message);
                if (message.equals("logout")) {
                    this.active = false;
                    this.s.close();
                    break;
                }

                if (message.startsWith("drawing")) {
                    for (ServerThread t : Server.list) {
                        if (t != this && t.active) {
                            t.os.writeUTF(message);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Client disconnected: " + this.name);
        } finally {
            try {
                this.is.close();
                this.os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}