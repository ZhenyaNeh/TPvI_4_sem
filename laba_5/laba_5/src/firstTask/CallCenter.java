package firstTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CallCenter {
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String BLUE = "\u001B[34m";
    public static final String RED = "\u001B[31m";
    private final int NUMBER_OF_OPERATORS = 5;
    private int currentNumberOfClients = 0;
    private final Client[] clients = new Client[NUMBER_OF_OPERATORS];

    public void acceptClient(Client client){
        long end = System.currentTimeMillis() + 5000;
        synchronized (this){
            while (currentNumberOfClients >= NUMBER_OF_OPERATORS){
                try{
                    wait();
                }
                catch (InterruptedException ex){
                    throw new RuntimeException(ex);
                }
            }
            if(System.currentTimeMillis() > end){
                int option = new Random().nextInt(2) + 1;
                switch (option){
                    case 1 -> {
                        System.out.println(RED + "Client " +  client.getClientName() + " passed out" + RESET);
                        return;
                    }
                    case 2 -> {
                        System.out.println(RED + "Client " + client.getClientName() + " passed out" + RESET);
                        try{
                            Thread.sleep(2000);
                        }
                        catch (InterruptedException ex){
                            throw new RuntimeException(ex);
                        }
                        System.out.println(GREEN + "Client " + client.getClientName() + " calls back" + RESET);
                    }
                }
            }
            currentNumberOfClients++;
            int operatorId = getFreeOperator();
            clients[operatorId] = client;
            System.out.println(GREEN + "Client " + client.getClientName() + " serviced by the operator "
                    + (operatorId + 1) + ". Current number of clients: " + currentNumberOfClients + RESET);
        }
        try{
            long timeForPerson = (long) 1000 * (new Random().nextInt(1,6) + 1);
            Thread.sleep(timeForPerson);
        }
        catch (InterruptedException ex){
            throw new RuntimeException(ex);
        }
        synchronized (this){
            clientLeaves(client);
            currentNumberOfClients--;
            notifyAll();
            System.out.println(BLUE + "Client " + client.getClientName() + " leaving the call center "
                    + ". Current number of clients: " + currentNumberOfClients + RESET);
            client.interrupt();
        }
    }

    private int getFreeOperator() {
        for (int i = 0; i < NUMBER_OF_OPERATORS; i++) {
            if (clients[i] == null)
                return i;
        }
        return -1;
    }

    public void clientLeaves(Client client) {
        for (int i = 0; i < NUMBER_OF_OPERATORS; i++) {
            if (clients[i] == client) {
                clients[i] = null;
            }
        }
    }

}
