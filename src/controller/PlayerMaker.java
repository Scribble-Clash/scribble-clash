package controller;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import api.PlayerAPI;
import data.Multiplayer;
import entity.Player;
import views.GamePanel;

public class PlayerMaker {
    private ArrayList<Player> players = new ArrayList<>();
    BufferedImage image;

    public Player createPlayer(int x, int y, GamePanel panel) {
        Loader load = new Loader();
        image = (BufferedImage) load.mainimage();

        // UUID uuid = UUID.randomUUID();
        // String id = uuid.toString();

        Player player = new Player(x, y, 1, panel, Multiplayer.roomCode, Multiplayer.id);
        PlayerAPI playerAPI = new PlayerAPI(Multiplayer.roomCode, Multiplayer.id);
        playerAPI.listenPlayerData(player);
        data.Players.addData(playerAPI);
        data.Players.addPlayerdata(player);

        return player;
    }

    public Player addPlayer(int x, int y, GamePanel panel, int numbers) {
        // UUID uuid = UUID.randomUUID();
        // String id = uuid.toString();
        Player player = new Player(x, y, 1, panel, Multiplayer.roomCode, "player" + numbers);
        PlayerAPI playerAPI = new PlayerAPI(Multiplayer.roomCode, "players" + numbers);
        playerAPI.listenPlayerData(player);
        data.Players.addData(playerAPI);
        data.Players.addPlayerdata(player);
        return player;
    }

    public ArrayList<entity.Player> getPlayers() {
        return players;
    }

}
