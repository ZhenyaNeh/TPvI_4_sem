package secondTask.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Server {
    public static final int PORT = 4000;
    public static LinkedList<ServerThread> serverThreads = new LinkedList<>();
    public static Story messageStory;

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(PORT);
        messageStory = new Story();
        System.out.println("Server is listening on port " + PORT);
        try {
            while (true) {
                Socket socket = server.accept();
                try {
                    serverThreads.add(new ServerThread(socket));
                }
                catch (IOException e) {
                    socket.close();
                }
            }
        }
        finally {
            server.close();
        }
    }
}
