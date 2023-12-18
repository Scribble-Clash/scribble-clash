package api;

import com.google.firebase.database.*;
import data.Multiplayer;
import views.GamePanel;
import views.GameWindow;

import java.util.HashMap;

public class Room {
    private static class Player {
        private String id;
        public int status;

        public Player() {

        }

        public Player(String id, int status) {
            this.id = id;
            this.status = status;
        }
    }

    private String roomCode;
    private boolean start = false;

    public Room() {
    }

    public Room(String roomCode, String id, Integer status) {
        this.roomCode = roomCode;

        listenPlayerList();
        addPlayer(id, status);
    }

    public Room(String roomCode) {
        this.roomCode = roomCode;
        listenPlayerList();
    }

    public void addPlayer(String id, Integer status) {
        Multiplayer.PlayerList.put(id, status);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();

        DatabaseReference positionRef = ref.child(this.roomCode);
        // add each player from playerlist to db
        for (String player : Multiplayer.PlayerList.keySet()) {
            positionRef.child(player).setValueAsync(new Player(player, Multiplayer.PlayerList.get(player)));
        }
    }

    public boolean isPlayerExist(String id) {
        for (String player : Multiplayer.PlayerList.keySet()) {
            if (player.equals(id)) {
                return true;
            }
        }
        return false;
    }

    public void listenPlayerList() {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference().child(this.roomCode);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot player : dataSnapshot.getChildren()) {
                    System.out.println(player.getKey());
                    System.out.println(player.getValue(Player.class).status);
                    Multiplayer.PlayerList.put(player.getKey(), player.getValue(Player.class).status);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }

    public HashMap<String, Integer> getPlayerList() {
        return Multiplayer.PlayerList;
    }

    public boolean isEmpty() {
        return Multiplayer.PlayerList.isEmpty();
    }

    public void setStart(boolean start) {
        this.start = start;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference().child(this.roomCode);
        ref.child("start").setValueAsync(start);
        this.start = !this.start;
        ref.child("start").setValueAsync(this.start);
    }

    public void listenStart(GameWindow gameWindow) {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference().child(this.roomCode).child("start");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                start = dataSnapshot.getValue(Boolean.class);
                if (start) {
                    gameWindow.showGamePanel(false);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }


}


