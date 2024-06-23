package thirdTask;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Server {
    private final DatagramSocket socket;

    public Server(int port) throws SocketException{
        socket = new DatagramSocket(port);
    }

    public String getMessages() throws IOException{
        byte[] messageUPD = new byte[14];
        socket.receive(new DatagramPacket(messageUPD, 14));
        StringBuilder message = new StringBuilder();
        for(var b: messageUPD){
            if(b != 0)
                message.append((char) b);
        }
        return message.toString();
    }

    public static void main(String[] args) throws IOException{
        Server getUPD = new Server(4321);
        while (true){
            System.out.println(getUPD.getMessages());
        }
    }
}
