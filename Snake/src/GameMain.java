import javax.swing.*;

public class GameMain extends JFrame {
    private final static int windowWidth = 1000;
    private final static int windowHeight = 800;

    public static void main(String[] args) {
        JFrame frame = new GameInstant(windowWidth, windowHeight);
        frame.setTitle("Snake Game");
        frame.setSize(windowWidth, windowHeight);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(false);
        frame.setVisible(true);
    }
}
