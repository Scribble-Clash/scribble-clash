package views;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

import javax.swing.*;

import controller.GameMap;
import controller.KeyInput;
import controller.Loader;
import controller.MouseInput;
import controller.PlayerMaker;
import entity.DummyEnemy;
import entity.Player;
import entity.Wall;
import entity.Weapon.Sword;
import entity.Weapon.Weapon;

public class GamePanel extends JPanel implements Runnable {
    private Player player;
    private PlayerMaker playerMaker;
    private Thread gameThread;
    public ArrayList<Wall> walls = new ArrayList<>();
    private GameMap gameMap;
    private MouseInput mouseInput;
    private KeyInput keychecker;
    private DummyEnemy dummyEnemy;

    public GamePanel() {
        playerMaker = new PlayerMaker();
        player = playerMaker.createPlayer(500, 900, this);

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
        gameThread = new Thread(this);
        gameThread.start();
    }

    public KeyInput getKeyChecker() {
        return keychecker;
    }

    @Override
    public void run() {
        while (true) {
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
