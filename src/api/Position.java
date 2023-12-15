package api;

import com.google.firebase.database.*;
import data.staticPos;

public class Position {
    public int x;
    public int y;

    public Position() {

    }

    public Position(String roomCode, String username, int x, int y) {
        this.x = x;
        this.y = y;

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();

        DatabaseReference positionRef = ref.child(roomCode).child(username);
        positionRef.setValueAsync(this);
    }

    public static void listenPos(String roomCode, String username) {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        ref.child(roomCode).child(username).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Position pos = dataSnapshot.getValue(Position.class);
                staticPos.x = pos.x;
                staticPos.y = pos.y;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }
}
