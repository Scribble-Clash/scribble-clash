package api;

import com.google.firebase.database.*;
import entity.Player;

public class PlayerAPI {
    private String roomCode;
    private String username;

    public int posX = 0;
    public int posY = 0;
    public int handX = 0;
    public int handY = 0;
    public int health = 0;
    public int weapon = 0;

    public PlayerAPI() {
    }

    public PlayerAPI(String roomCode, String username) {
        this.roomCode = roomCode;
        this.username = username;
//        listenPos();
    }

    public void setPlayerPosition(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();

        DatabaseReference positionRef = ref.child(roomCode).child(username);
        positionRef.setValueAsync(this);
    }

    public void listenPlayerData(Player player) {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference().child(roomCode).child(username);
        ref.child("posX").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Integer posX = dataSnapshot.getValue(Integer.class);
                player.setXPosition(posX);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
        ref.child("posY").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Integer posY = dataSnapshot.getValue(Integer.class);
                player.setYPosition(posY);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

        ref.child("handX").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Integer handX = dataSnapshot.getValue(Integer.class);
                player.setHandXPosition(handX);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

        ref.child("handY").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Integer handY = dataSnapshot.getValue(Integer.class);
                player.setHandYPosition(handY);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

        ref.child("health").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Integer health = dataSnapshot.getValue(Integer.class);
                player.setHealth(health);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

    }

    public void setHandPosition(int handX, int handY) {
        this.handX = handX;
        this.handY = handY;
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();

        DatabaseReference positionRef = ref.child(roomCode).child(username);
        positionRef.setValueAsync(this);
    }

    public void setHealth(int health) {
        this.health = health;
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();

        DatabaseReference positionRef = ref.child(roomCode).child(username);
        positionRef.setValueAsync(this);
    }
}
