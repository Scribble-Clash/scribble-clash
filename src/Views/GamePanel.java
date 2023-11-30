package Views;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Controllers.*;
import Entity.*;

public class GamePanel extends javax.swing.JPanel implements Runnable, ActionListener {
    private Player player;
    private Thread gameThread;
    private WeaponController weaponController;
    public ArrayList<Wall> walls = new ArrayList<>();
    String groundImagePath = "D:\\#Programing\\Project\\Fight-Scribble\\Fight-ScribbleGit\\scribble-fight\\assets\\rumput.png";
    String wallImagePath = "D:\\#Programing\\Project\\Fight-Scribble\\Fight-ScribbleGit\\scribble-fight\\assets\\wall.png";
    String player1imagepath = "D:\\#Programing\\Project\\Fight-Scribble\\Fight-ScribbleGit\\scribble-fight\\assets\\redchara.png";
    String hand1imagepath = "D:\\#Programing\\Project\\Fight-Scribble\\Fight-ScribbleGit\\scribble-fight\\assets\\redhand.png";

    public GamePanel() {

        Image charaimg = Loader.loadImage(player1imagepath);
        Image handimg = Loader.loadImage(hand1imagepath);

        player = new Player(400, 300, charaimg, handimg, this);
        this.addKeyListener(new KeyChecker(this));

        // menambahkan ground baru
        Image wallImage = Loader.loadImage(wallImagePath);
        addwall(wallImage, 50, 2000);
        Image groundimage = Loader.loadImage(groundImagePath);
        addground(groundimage, 50, 2000);

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

    public void addground(Image img, int width, int height) {
        for (int i = 0; i < 1900; i += width) {
            walls.add(new Wall(i, 1000, img));
        }
    }

    public void addwall(Image img, int width, int height) {
        for (int i = 0; i < 1900; i += width) {
            walls.add(new Wall(0, i, img));
        }
        for (int i = 0; i < 1900; i += width) {
            walls.add(new Wall(1850, i, img));
        }
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

    public void handleMouseInput(int mouseX, int mouseY) {
        // Mendapatkan posisi tangan pemain
        Point handPosition = player.getHandPosition();

        // Menghitung sudut rotasi
        double angle = Math.atan2(mouseY - handPosition.y, mouseX - handPosition.x);
        int rotationAngle = (int) Math.toDegrees(angle);

        // Mengatur orientasi senjata
        player.getHand().setOrientation(rotationAngle);

        // Menggerakkan senjata lain jika ada
        weaponController.moveOtherWeapons(rotationAngle);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}