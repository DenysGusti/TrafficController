import java.util.HashMap;
import java.util.Map;

public class TrafficRegistrarEnhanced implements TrafficRegistrar {

    private final Map<Integer, String> carsOnBridge = new HashMap<>();

    @Override
    public synchronized void registerLeft(Vehicle v) {
        carsOnBridge.put(v.getId(), "left");
        System.out.println("Vehicle " + v.getId() + " registered entering from the left.");
        outputCarsOnBridge();
    }

    @Override
    public synchronized void registerRight(Vehicle v) {
        carsOnBridge.put(v.getId(), "right");
        System.out.println("Vehicle " + v.getId() + " registered entering from the right.");
        outputCarsOnBridge();
    }

    @Override
    public synchronized void deregisterLeft(Vehicle v) {
        carsOnBridge.remove(v.getId());
        System.out.println("Vehicle " + v.getId() + " deregistered leaving to the left.");
        outputCarsOnBridge();
    }

    @Override
    public synchronized void deregisterRight(Vehicle v) {
        carsOnBridge.remove(v.getId());
        System.out.println("Vehicle " + v.getId() + " deregistered leaving to the right.");
        outputCarsOnBridge();
    }

    private void outputCarsOnBridge() {
        System.out.println("Cars currently on the bridge: " + carsOnBridge.size());
        for (var car : carsOnBridge.entrySet())
            System.out.println("Car " + car.getKey() + " entered from the " + car.getValue());
        System.out.println();
    }
}
