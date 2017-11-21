package kmitl.mobile.project.bawonsak58070074.tradeevent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class MainActivity extends AnimateIntent {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
        /*DatabaseReference mUsersRef = mRootRef.child("member");
        DatabaseReference mMessagesRef = mRootRef.child("event");
        DatabaseReference member = mUsersRef.child("Kcomic");
        member.child("username").setValue("Kcomic");
        member.child("password").setValue("as22102539");
        member.child("Email").setValue("Bawonsak1996@gmail.com");
        member.child("rating").setValue("0");

        member = mUsersRef.child("Bawonsak");
        member.child("username").setValue("Bawonsak");
        member.child("password").setValue("as22102539");
        member.child("Email").setValue("Bawonsak1996");
        member.child("rating").setValue("0");*/

        mRootRef.child("member").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    System.out.println(ds.getKey());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //Log.e(TAG, databaseError.getMessage());
                System.out.println("error");
            }
        });


    }
}
