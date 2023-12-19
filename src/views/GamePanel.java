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
import data.Multiplayer;
import entity.BgrAssets;
import entity.DummyEnemy;
import entity.Player;
import entity.Wall;

public class GamePanel extends JPanel implements Runnable {
    private Player player;
    private ArrayList<Player> otherPlayer = new ArrayList<>();
    private Thread gameThread;
    public ArrayList<Wall> walls = new ArrayList<>();
    public ArrayList<BgrAssets> asets = new ArrayList<>();
    private GameMap gameMap;
    private MouseInput mouseInput;
    private KeyInput keychecker;
    private DummyEnemy dummyEnemy;
    private volatile boolean running = true;
    private boolean isDemo;

    public GamePanel(boolean isDemo) {
        this.isDemo = isDemo;
        initComponents();
    }

    private void initComponents() {
        player = new PlayerMaker().createPlayer(120, 400, this);

        for (int i = 2; i <= Multiplayer.numberOfPlayers; i++) {
            otherPlayer.add(new PlayerMaker().addPlayer(1630, 400, this, i));
        }
        keychecker = new KeyInput(player);
        addKeyListener(keychecker);

        mouseInput = new MouseInput(player);
        addMouseListener(mouseInput);
        addMouseMotionListener(mouseInput);

        gameMap = new GameMap(this);

        gameMap.Map1();
        walls = gameMap.getWalls();
        asets = gameMap.getAssets();
        Loader load = new Loader();
        if (isDemo) {
            BufferedImage dummyEnemyImage = (BufferedImage) load.mainimage();
            dummyEnemy = new DummyEnemy(900, 700, dummyEnemyImage.getSubimage(576, 128, 64, 64), this);
        }
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
            if (isDemo)
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
        if (isDemo)
            dummyEnemy.draw(gtd);
        for (Player otherPlayer : this.otherPlayer) {
            otherPlayer.draw(gtd);
        }
        for (Wall wall : walls) {
            wall.draw(gtd);
        }
        for (BgrAssets asets : asets) {
            asets.draw(gtd);
        }
    }
}
