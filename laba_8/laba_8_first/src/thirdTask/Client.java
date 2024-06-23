package thirdTask;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Client {
    private final DatagramSocket socket;

    public  Client(int port) throws SocketException{
        socket = new DatagramSocket(port);
    }

    public void sendMessage(String messages, int port) throws IOException{
        DatagramPacket packet = new DatagramPacket(messages.getBytes(), messages.getBytes().length, InetAddress.getLocalHost(), port);
        socket.send(packet);
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client(1234);
        client.sendMessage("Hello client 1", 4321);
        client.sendMessage("Hello client 2", 4321);
        client.sendMessage("Hello client 3", 4321);
        client.sendMessage("end", 4321);

    }
}
