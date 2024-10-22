import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TrafficControllerFair implements TrafficController {

    private final TrafficRegistrar registrar;
    private final Lock lock = new ReentrantLock(true);
    private final Condition bridgeFree = lock.newCondition();
    private boolean bridgeOccupied = false;

    public TrafficControllerFair(TrafficRegistrar r) {
        this.registrar = r;
    }

    @Override
    public void enterRight(Vehicle v) {
        lock.lock();
        try {
            // if while were outside and bridgeOccupied were false,
            // finally would never run and the lock would never unlock
            while (bridgeOccupied)
                bridgeFree.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }

        bridgeOccupied = true;
        registrar.registerRight(v);
        System.out.println("Vehicle " + v.getId() + " entered from the right.");
    }

    @Override
    public void enterLeft(Vehicle v) {
        lock.lock();
        try {
            // if while were outside and bridgeOccupied were false,
            // finally would never run and the lock would never unlock
            while (bridgeOccupied)
                bridgeFree.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }

        bridgeOccupied = true;
        registrar.registerLeft(v);
        System.out.println("Vehicle " + v.getId() + " entered from the left.");
    }

    @Override
    public void leaveLeft(Vehicle v) {
        lock.lock();
        try {
            registrar.deregisterLeft(v);
            System.out.println("Vehicle " + v.getId() + " left to the left.");
            bridgeOccupied = false;
            bridgeFree.signal();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void leaveRight(Vehicle v) {
        lock.lock();
        try {
            registrar.deregisterRight(v);
            System.out.println("Vehicle " + v.getId() + " left to the right.");
            bridgeOccupied = false;
            bridgeFree.signal();
        } finally {
            lock.unlock();
        }
    }
}