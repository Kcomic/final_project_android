package kmitl.mobile.project.bawonsak58070074.tradeevent;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import kmitl.mobile.project.bawonsak58070074.tradeevent.Adapter.MemberAdapter;
import kmitl.mobile.project.bawonsak58070074.tradeevent.model.Member;

public class ProfileSomeone extends AppCompatActivity {

    private TextView name, phone, rating, ratingUp;
    private Member member;
    private String username;
    ImageView profile_image;
    private DatabaseReference mRootRef, mUsersRef;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_someone);
        member = getIntent().getParcelableExtra("member");
        mRootRef = FirebaseDatabase.getInstance().getReference();
        mUsersRef = mRootRef.child("member");
        username = member.getUsername();
        setup();
    }

    private void setup() {
        profile_image = findViewById(R.id.profile_image_someone);
        phone = findViewById(R.id.phonePro_someone);
        rating = findViewById(R.id.ratingPro_someone);
        ratingUp = findViewById(R.id.ratingUp_someone);

        name = findViewById(R.id.fullname_someone);

        ratingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vote();
            }
        });
        showProfile();
    }

    private void showProfile() {
        if (!member.getUrl().equals("new"))
            Glide.with(this).load(member.getUrl()).into(profile_image);
        name.setText(member.getFullname());
        phone.setText(member.getPhone());
        rating.setText(member.getRating());

    }

    private void vote() {

        AlertDialog.Builder builder =
                new AlertDialog.Builder(this);
        builder.setMessage("ํYou want to vote this user?");
        builder.setPositiveButton("Vote", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                mUsersRef.child(username).child("rating").setValue(String.valueOf(Integer.valueOf(member.getRating()) + 1));
                Toast.makeText(getApplicationContext(), "Vote Complete!", Toast.LENGTH_SHORT).show();
                member.setRating(String.valueOf(Integer.valueOf(member.getRating()) + 1));
                rating.setText(member.getRating());
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //dialog.dismiss();
            }
        });
        builder.show();

    }

}
