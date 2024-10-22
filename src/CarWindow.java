import java.awt.*;
import javax.swing.*;

public class CarWindow extends JFrame {

    CarWorld display;
    JButton addLeft;
    JButton addRight;

    public CarWindow() {
        Container c = getContentPane();

        c.setLayout(new BorderLayout());
        display = new CarWorld();


        c.add("Center", display);
        addLeft = new JButton("Add Left");
        addRight = new JButton("Add Right");

        addLeft.addActionListener(_ -> display.addCar(Car.REDCAR));

        addRight.addActionListener(_ -> display.addCar(Car.BLUECAR));


        var p1 = new JPanel();
        p1.setLayout(new FlowLayout());
        p1.add(addLeft);
        p1.add(addRight);
        c.add("South", p1);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}
