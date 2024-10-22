import java.awt.*;
import java.util.*;
import javax.swing.*;

class CarWorld extends JPanel {

    Image bridge;
    Image redCar;
    Image blueCar;

    TrafficController controller;

    ArrayList<Car> blueCars = new ArrayList<>();
    ArrayList<Car> redCars = new ArrayList<>();

    public CarWorld() {
        //controller = new TrafficControllerEmpty(new TrafficRegistrarEmpty());
        //controller = new TrafficControllerSimple(new TrafficRegistrarEmpty());
        controller = new TrafficControllerFair(new TrafficRegistrarEnhanced());

        MediaTracker mt = new MediaTracker(this);
        Toolkit toolkit = Toolkit.getDefaultToolkit();

        redCar = toolkit.getImage("image/redcar.gif");
        mt.addImage(redCar, 0);
        blueCar = toolkit.getImage("image/bluecar.gif");
        mt.addImage(blueCar, 1);
        bridge = toolkit.getImage("image/bridge.gif");
        mt.addImage(bridge, 2);

        try {
            mt.waitForID(0);
            mt.waitForID(1);
            mt.waitForID(2);
        } catch (java.lang.InterruptedException e) {
            System.out.println("Couldn't load one of the images");
        }

        redCars.add(new Car(Car.REDCAR, null, redCar, null));
        blueCars.add(new Car(Car.BLUECAR, null, blueCar, null));
        setPreferredSize(new Dimension(bridge.getWidth(null), bridge.getHeight(null)));
    }


    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(bridge, 0, 0, this);
        for (Car c : redCars) c.draw(g);
        for (Car c : blueCars) c.draw(g);
    }

    public void addCar(final int cartype) {
        SwingUtilities.invokeLater(() -> {
            Car c;
            if (cartype == Car.REDCAR) {
                c = new Car(cartype, redCars.getLast(), redCar, controller);
                redCars.add(c);
            } else {
                c = new Car(cartype, blueCars.getLast(), blueCar, controller);
                blueCars.add(c);
            }
            new Thread(c).start();
        });
    }

}
