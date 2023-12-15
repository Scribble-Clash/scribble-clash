package data;

import api.PlayerAPI;

import java.util.ArrayList;

public class Players {
    private static ArrayList<PlayerAPI> data = new ArrayList<>();

    public static PlayerAPI getData(int index) {
        return data.get(index);
    }
    public static void addData(PlayerAPI player) {
        data.add(player);
    }
}