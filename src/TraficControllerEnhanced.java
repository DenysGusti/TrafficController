public class TraficControllerEnhanced implements TrafficController {

    private static final int MAX_LEFT_CARS_ON_BRIDGE = 2;
    private static final int MAX_RIGHT_CARS_ON_BRIDGE = 1;

    private final TrafficRegistrar registrar;
    private int leftVehicleCount = 0;
    private int rightVehicleCount = 0;

    public TraficControllerEnhanced(TrafficRegistrar r) {
        this.registrar = r;
    }

    @Override
    public synchronized void enterRight(Vehicle v) {
        try {
            while (rightVehicleCount >= MAX_RIGHT_CARS_ON_BRIDGE || leftVehicleCount > 0)
                wait();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        ++rightVehicleCount;
        registrar.registerRight(v);
        System.out.println("Vehicle " + v.getId() + " entered from the right.");
    }

    @Override
    public synchronized void enterLeft(Vehicle v) {
        try {
            while (leftVehicleCount >= MAX_LEFT_CARS_ON_BRIDGE || rightVehicleCount > 0)
                wait();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        ++leftVehicleCount;
        registrar.registerLeft(v);
        System.out.println("Vehicle " + v.getId() + " entered from the left.");
    }

    @Override
    public synchronized void leaveLeft(Vehicle v) {
        registrar.deregisterLeft(v);
        System.out.println("Vehicle " + v.getId() + " left to the left.");
        --rightVehicleCount;
        notifyAll();
    }

    @Override
    public synchronized void leaveRight(Vehicle v) {
        registrar.deregisterRight(v);
        System.out.println("Vehicle " + v.getId() + " left to the right.");
        --leftVehicleCount;
        notifyAll();
    }
}
