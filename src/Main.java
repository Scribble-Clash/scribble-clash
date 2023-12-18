import javax.swing.JFrame;

import api.Firebase;
import views.GameWindow;

public class Main {
    public static void main(String[] args) {
        try {
            new Firebase();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        GameWindow frame = new GameWindow();
        frame.setResizable(false);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setTitle("Scribble Clash");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
