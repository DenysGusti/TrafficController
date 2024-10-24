public class TrafficControllerSimple implements TrafficController {

    private final TrafficRegistrar registrar;
    private boolean bridgeOccupied = false;

    public TrafficControllerSimple(TrafficRegistrar r) {
        this.registrar = r;
    }

    @Override
    public synchronized void enterRight(Vehicle v) {
        try {
            while (bridgeOccupied)
                wait();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        bridgeOccupied = true;
        registrar.registerRight(v);
        System.out.println("Vehicle " + v.getId() + " entered from the right.");
    }

    @Override
    public synchronized void enterLeft(Vehicle v) {
        try {
            while (bridgeOccupied)
                wait();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        bridgeOccupied = true;
        registrar.registerLeft(v);
        System.out.println("Vehicle " + v.getId() + " entered from the left.");
    }

    @Override
    public synchronized void leaveLeft(Vehicle v) {
        registrar.deregisterLeft(v);
        System.out.println("Vehicle " + v.getId() + " left to the left.");
        bridgeOccupied = false;
        notifyAll();
    }

    @Override
    public synchronized void leaveRight(Vehicle v) {
        registrar.deregisterRight(v);
        System.out.println("Vehicle " + v.getId() + " left to the right.");
        bridgeOccupied = false;
        notifyAll();
    }
}
