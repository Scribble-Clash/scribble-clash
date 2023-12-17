package api;

import com.google.firebase.database.*;
import entity.Player;

public class PlayerAPI {
    private String roomCode;
    private String id;

    public int posX = 0;
    public int posY = 0;
    public int handX = 0;
    public int handY = 0;
    public int health = 100;
    public Double speedX = 0.0;
    public Double speedY = 0.0;
    public int weapon = 0;
    public int hit = 0;
    public int charge = 0;

    public PlayerAPI() {
    }

    public PlayerAPI(String roomCode, String id) {
        this.roomCode = roomCode;
        this.id = id;
        setHealth(100);
//        listenPos();
    }

    public String getId() {
        return id;
    }

    public void setPlayerPosition(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();

        DatabaseReference positionRef = ref.child(roomCode).child(id);
        positionRef.setValueAsync(this);
    }

    public void listenPlayerData(Player player) {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference().child(roomCode).child(id);
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
                player.setFirebaseHealth(health);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
        ref.child("hit").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Integer hit = dataSnapshot.getValue(Integer.class);
                System.out.println(hit);
                if (hit == 1)
                    if (player.getHeldWeapon() == null) {
                        player.getHand().attack();
                    } else {
                        player.getHeldWeapon().attack();
                    }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ref.child("speedX").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Double speed = dataSnapshot.getValue(Double.class);
                player.setXSpeed(speed);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ref.child("speedY").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Double speed = dataSnapshot.getValue(Double.class);
                player.setYSpeed(speed);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void setSpeed(Double speedX, Double speedY) {
        this.speedX = speedX;
        this.speedY = speedY;
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();

        DatabaseReference positionRef = ref.child(roomCode).child(id);
        positionRef.setValueAsync(this);
    }

    public void setHandPosition(int handX, int handY) {
        this.handX = handX;
        this.handY = handY;
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();

        DatabaseReference positionRef = ref.child(roomCode).child(id);
        positionRef.setValueAsync(this);
    }

    public void setHealth(int health) {
        this.health = health;
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();

        DatabaseReference positionRef = ref.child(roomCode).child(id);
        positionRef.setValueAsync(this);
    }

    public void setHit(int hit) {
        this.hit = hit;
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();

        DatabaseReference positionRef = ref.child(roomCode).child(id);
        positionRef.setValueAsync(this);
    }
}
