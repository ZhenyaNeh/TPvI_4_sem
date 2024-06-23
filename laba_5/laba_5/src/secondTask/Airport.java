package secondTask;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Airport {
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String BLUE = "\u001B[34m";
    public static final String YELLOW = "\u001B[33m";
    public static final String PURPLE = "\u001B[35m";
    private Semaphore terminalSemaphore;
    private Semaphore trapesSemaphore;
    private CyclicBarrier boardingBarrier;

    public Airport(int capacity, int numTerminal, int numTrapes){
        terminalSemaphore = new Semaphore(numTerminal);
        trapesSemaphore = new Semaphore(numTrapes);
        boardingBarrier = new CyclicBarrier(capacity);
    }

    public void boardingPassenger(int planeId, int passengerId) throws InterruptedException{
        System.out.println(GREEN + "Passenger " + passengerId + " passed" + RESET);

        terminalSemaphore.acquire();
        System.out.println(YELLOW + "Passenger " + passengerId + " entered the terminal"  + RESET);
        terminalSemaphore.release();

        trapesSemaphore.acquire();
        System.out.println(PURPLE + "Passenger " + passengerId + " walked onto the plane " + planeId + RESET);
        trapesSemaphore.release();

        Thread.sleep(1000);

        try{
            boardingBarrier.await();
        }
        catch(BrokenBarrierException ex){
            ex.printStackTrace();
        }

        System.out.println(BLUE + "Passenger " + passengerId + " got on the plane " + planeId + RESET);
    }
}
