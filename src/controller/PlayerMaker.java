package controller;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import api.PlayerAPI;
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

//        UUID uuid = UUID.randomUUID();
//        String id = uuid.toString();
        String id = "player1";

        Player player = new Player(x, y, 1, panel, "ABCD", id);
        PlayerAPI playerAPI = new PlayerAPI("ABCD", "player1");
        data.Players.addData(playerAPI);
        return player;
    }

    public Player addPlayer(int x, int y, GamePanel panel) {
//        UUID uuid = UUID.randomUUID();
//        String id = uuid.toString();
        String id = "saya";
        Player player = new Player(x, y, 1, panel, "ABCD", id);
        PlayerAPI playerAPI = new PlayerAPI("ABCD", "player2");
        data.Players.addData(playerAPI);
        return player;
    }

    public ArrayList<entity.Player> getPlayers() {
        return players;
    }

}
