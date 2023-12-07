
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import views.GameWindow;

public class Main {

    public static void main(String[] args) {
        GameWindow frame = new GameWindow();
        frame.setSize(1920, 1080);
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((int) (screensize.getWidth() / 2 - frame.getSize().getWidth() / 2),
                (int) (screensize.getHeight() / 2 - frame.getSize().getHeight() / 2));
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setResizable(false);
        frame.setTitle("Scrabble-Fight-The-Game");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(3);
    }
}
