package Views;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Controllers.KeyChecker;
import Entity.Player;
import Entity.Wall;

public class GamePanel extends javax.swing.JPanel implements Runnable, ActionListener {
    private Player player;
    private Thread gameThread;
    public ArrayList<Wall> walls = new ArrayList<>();

    public GamePanel() {
        player = new Player(400, 300, this);
        this.addKeyListener(new KeyChecker(this));
        makeWalls();

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (true) {
            player.set();
            repaint();
            try {
                Thread.sleep(17); // Delay untuk mengatur kecepatan pergerakan
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void makeWalls() {
        // Dinding bawah
        for (int i = 50; i < 1005; i += 50) {
            walls.add(new Wall(i, 620, 50, 50));
        }
        // Dinding batas kiri
        for (int i = 0; i <= 650; i += 50) {
            walls.add(new Wall(0, i, 50, 50));
        }

        // Dinding batas kanan
        for (int i = 0; i <= 650; i += 50) {
            walls.add(new Wall(1010, i, 50, 50));
        }
        walls.add(new Wall(50, 550, 50, 50));
        walls.add(new Wall(50, 500, 50, 50));
        walls.add(new Wall(50, 450, 50, 50));

    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D gtd = (Graphics2D) g;
        player.draw(gtd);
        for (Wall wall : walls) {
            wall.draw(gtd);

        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'w') {
            player.keyUp = true;
        }
        if (e.getKeyChar() == 'a') {
            player.keyLeft = true;
        }
        if (e.getKeyChar() == 's') {
            player.keyDown = true;
        }
        if (e.getKeyChar() == 'd') {
            player.keyRight = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyChar() == 'w') {
            player.keyUp = false;
        }
        if (e.getKeyChar() == 'a') {
            player.keyLeft = false;
        }
        if (e.getKeyChar() == 's') {
            player.keyDown = false;
        }
        if (e.getKeyChar() == 'd') {
            player.keyRight = false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}