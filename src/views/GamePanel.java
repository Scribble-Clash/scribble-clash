package views;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.*;

import controller.GameMap;
import controller.KeyInput;
import controller.Loader;
import controller.MouseInput;
import controller.PlayerMaker;
import entity.DummyEnemy;
import entity.Player;
import entity.Wall;

public class GamePanel extends JPanel implements Runnable {
    private Player player;
    private Thread gameThread;
    public ArrayList<Wall> walls = new ArrayList<>();
    private GameMap gameMap;
    private MouseInput mouseInput;
    private KeyInput keychecker;
    private DummyEnemy dummyEnemy;
    private JLabel healthLabel;
    private boolean isPaused = false;
    private volatile boolean running = true;

    public GamePanel() {
        initComponents();
    }

    private void initComponents() {
        player = new PlayerMaker().createPlayer(500, 900, this);
        keychecker = new KeyInput(player);
        addKeyListener(keychecker);

        mouseInput = new MouseInput(player);
        addMouseListener(mouseInput);
        addMouseMotionListener(mouseInput);

        gameMap = new GameMap(this);
        gameMap.testMap();
        walls = gameMap.getWalls();

        Loader load = new Loader();
        BufferedImage dummyEnemyImage = (BufferedImage) load.mainimage();
        dummyEnemy = new DummyEnemy(900, 700, dummyEnemyImage.getSubimage(576, 128, 64, 64), this);

        healthLabel = new JLabel("Health: " + dummyEnemy.getHealth());
        healthLabel.setForeground(Color.RED);
        add(healthLabel);

        gameThread = new Thread(this);
        gameThread.start();

        // JButton pauseButton = new JButton("Pause");
        // pauseButton.addActionListener(new ActionListener() {
        // public void actionPerformed(ActionEvent e) {
        // isPaused = true;
        // pauseGame();
        // keychecker.setEnabled(false);
        // }
        // });
        // add(pauseButton);
    }

    @Override
    public void run() {
        while (running) {
            player.set();
            dummyEnemy.set();
            if (mouseInput.isMouseInside()) {
                player.updateHandPosition(mouseInput.getLastMouseX(), mouseInput.getLastMouseY());
            } else {
                player.updateHandPosition(mouseInput.getLastMouseX(), mouseInput.getLastMouseY());
            }
            repaint();
            try {
                Thread.sleep(15);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    // private void pauseGame() {
    // if (isPaused) {
    // new PauseFrame(this);
    // }
    // }

    // public void resumeGame() {
    // isPaused = false;
    // keychecker.setEnabled(true);
    // }

    // public void stopGame() {
    // running = false;
    // }

    public void returnToMainMenu() {
        Container parent = getParent();
        if (parent instanceof GameWindow) {
            ((GameWindow) parent).showMainMenu();
        }
    }

    public DummyEnemy getDummyEnemy() {
        return dummyEnemy;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D gtd = (Graphics2D) g;
        player.draw(gtd);
        dummyEnemy.draw(gtd);
        for (Wall wall : walls) {
            wall.draw(gtd);
        }
    }
}
