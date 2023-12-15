package api;

import com.google.firebase.database.*;

public class PlayerAPI {
    private String roomCode;
    private String username;

    public int posX = 0;
    public int posY = 0;
    public int handX = 0;
    public int handY = 0;
    public int health = 0;
    public int weapon = 0;

    public PlayerAPI(String roomCode, String username) {
        this.roomCode = roomCode;
        this.username = username;
        listenPos();
    }

    public void setPosition(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();

        DatabaseReference positionRef = ref.child(roomCode).child(username);
        positionRef.setValueAsync(this);
    }

    public void listenPos() {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        ref.child(roomCode).child(username).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                PlayerAPI pos = dataSnapshot.getValue(PlayerAPI.class);
                setPosition( posX, posY);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }
}
