package secondTask;

public class Main {
    public static void main(String[] args){
        int planeCapacity = 3;
        int numTerminals = 2;
        int numTraps = 1;
        int numPassengers = 12;

        Airport airport = new Airport(planeCapacity, numTerminals, numTraps);

        for (int i = 1; i <= numPassengers; i++) {
            int planeId = (i - 1) / planeCapacity + 1;

            new Passenger(i, planeId, airport).start();
        }
    }
}
