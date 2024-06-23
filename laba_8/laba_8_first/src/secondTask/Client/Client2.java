package secondTask.Client;

public class Client2 {
    private static final String IPAddress = "localhost";
    private static final int PORT = 4000;

    public static void main(String[] args) {
        new ClientThread(IPAddress, PORT);
    }
}
