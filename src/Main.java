import javax.swing.JFrame;
import views.GameWindow;

public class Main {
    public static void main(String[] args) {
        GameWindow frame = new GameWindow();
        frame.setResizable(false);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setTitle("Scrabble-Fight-The-Game");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
