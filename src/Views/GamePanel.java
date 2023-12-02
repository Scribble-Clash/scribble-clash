package views;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import controller.GameMap;
import controller.KeyChecker;
import controller.MouseInput;
import controller.PlayerMaker;
import entity.Player;
import entity.Wall;

public class GamePanel extends JPanel implements Runnable {
    private Player player; // Mengganti ArrayList<Player> menjadi objek tunggal Player
    private PlayerMaker playerMaker;
    private Thread gameThread;
    public ArrayList<Wall> walls = new ArrayList<>();
    private GameMap gameMap;
    private MouseInput mouseInput;

    public GamePanel() {
        playerMaker = new PlayerMaker();
        player = playerMaker.createPlayer(400, 900, this); // Menginisialisasi objek player
        this.addKeyListener(new KeyChecker(this));
        mouseInput = new MouseInput(player); // Menggunakan objek player sebagai argumen
        addMouseListener(mouseInput);
        addMouseMotionListener(mouseInput);

        gameMap = new GameMap(this);
        gameMap.testMap();
        walls = gameMap.getWalls();
        gameThread = new Thread(this);
        gameThread.start();
    }

    // thread yang digunakan menjadi gameloop
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

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                player.keyUp = true;
                break;
            case KeyEvent.VK_A:
                player.keyLeft = true;
                break;
            case KeyEvent.VK_S:
                player.keyDown = true;
                break;
            case KeyEvent.VK_D:
                player.keyRight = true;
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                player.keyUp = false;
                break;
            case KeyEvent.VK_A:
                player.keyLeft = false;
                break;
            case KeyEvent.VK_S:
                player.keyDown = false;
                break;
            case KeyEvent.VK_D:
                player.keyRight = false;
                break;
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D gtd = (Graphics2D) g;
        player.draw(gtd); // Menggambar objek player
        for (Wall wall : walls) {
            wall.draw(gtd);
        }
    }

}
