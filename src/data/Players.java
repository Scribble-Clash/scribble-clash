package data;

import api.PlayerAPI;
import entity.Player;

import java.util.ArrayList;

public class Players {
    private static ArrayList<PlayerAPI> data = new ArrayList<>();
    private static ArrayList<Player> playerdata = new ArrayList<>();

    public static Player getPlayerdata(int index) {
        return playerdata.get(index);
    }

    public static void addPlayerdata(Player player) {
        playerdata.add(player);
    }

    public static PlayerAPI getData(String id) {
        for (PlayerAPI player : data) {
            if (player.getId().equals(id)) {
                return player;
            }
        }
        return null;
    }

    public static void addData(PlayerAPI player) {
        data.add(player);
    }

    public static void removeData(PlayerAPI player) {
        data.remove(player);
    }

    public static boolean isEmpty() {
        return data.isEmpty();
    }
}