public class TrafficRegistrarEnhanced implements TrafficRegistrar {

    private static final int MAX_CARS_ON_BRIDGE = 1;

    private int carsOnBridge = 0;

    @Override
    public synchronized void registerLeft(Vehicle v) {
        if (carsOnBridge >= MAX_CARS_ON_BRIDGE)
            System.out.println("Too many cars on the bridge! Current cars: " + carsOnBridge);

        ++carsOnBridge;
        System.out.println("Vehicle " + v.getId() + " registered entering from the left.");
    }

    @Override
    public synchronized void registerRight(Vehicle v) {
        if (carsOnBridge >= MAX_CARS_ON_BRIDGE)
            System.out.println("Too many cars on the bridge! Current cars: " + carsOnBridge);

        ++carsOnBridge;
        System.out.println("Vehicle " + v.getId() + " registered entering from the right.");
    }

    @Override
    public synchronized void deregisterLeft(Vehicle v) {
        if (carsOnBridge == 0)
            System.out.println("No cars on the bridge! Vehicle " + v.getId() + " tried to leave.");

        --carsOnBridge;
        System.out.println("Vehicle " + v.getId() + " deregistered leaving to the left.");
    }

    @Override
    public synchronized void deregisterRight(Vehicle v) {
        if (carsOnBridge == 0)
            System.out.println("No cars on the bridge! Vehicle " + v.getId() + " tried to leave.");

        --carsOnBridge;
        System.out.println("Vehicle " + v.getId() + " deregistered leaving to the right.");
    }
}
