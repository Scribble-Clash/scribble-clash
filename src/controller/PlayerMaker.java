package controller;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entity.Player;
import views.GamePanel;

public class PlayerMaker {
    private ArrayList<Player> players = new ArrayList<>();
    BufferedImage image;

    public Player createPlayer(int x, int y, GamePanel panel) {
        Loader load = new Loader();
        image = (BufferedImage) load.mainimage();
        Image bodyimg = image.getSubimage(576, 128, 64, 64);
        Image handimg = image.getSubimage(640, 0, 64, 64);

        Player player1 = new Player(x, y, bodyimg, handimg, panel);
        players.add(player1);
        return player1;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

}
