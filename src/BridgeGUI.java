public class BridgeGUI {

    private static void nap(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException _) {
        }
    }


    public static void main(String[] a) {
        final var win = new CarWindow();

        win.pack();
        win.setVisible(true);

        new Thread(() -> {
            while (true) {
                nap(25);
                win.repaint();
            }
        }).start();
    }

}
