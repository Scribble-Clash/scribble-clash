import java.awt.Dimension;
import java.awt.Toolkit;

import Views.MainFrame;

public class Main {

    public static void main(String[] args) {

        MainFrame frame = new MainFrame();

        frame.setSize(1080, 720);
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((int) (screensize.getWidth() / 2 - frame.getSize().getWidth() / 2),
                (int) (screensize.getHeight() / 2 - frame.getSize().getHeight() / 2));
        frame.setResizable(false);
        frame.setTitle("Scrabble");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(1);
    }
}
