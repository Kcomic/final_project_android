package kmitl.mobile.project.bawonsak58070074.tradeevent;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;

import kmitl.mobile.project.bawonsak58070074.tradeevent.Adapter.Holder.MemberHolder;
import kmitl.mobile.project.bawonsak58070074.tradeevent.model.Member;

import static android.app.Activity.RESULT_OK;

public class ProfileFragment extends Fragment {

    private static final int PICK_SOMETHING = 1;
    private StorageReference storageReference;
    private TextView name, phone, rating, ratingUp;
    private Uri uri;
    private int check;
    private Member member;
    private String username;
    ImageView profile_image;
    private DatabaseReference mRootRef, mUsersRef;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(Member member, int check) {
        Bundle args = new Bundle();
        ProfileFragment fragment = new ProfileFragment();
        args.putParcelable("member", member);
        args.putInt("check", check);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        storageReference = FirebaseStorage.getInstance().getReference();
        member = getArguments().getParcelable("member");
        check = getArguments().getInt("check");
        mRootRef = FirebaseDatabase.getInstance().getReference();
        mUsersRef = mRootRef.child("member");
        username = member.getUsername();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        profile_image = rootView.findViewById(R.id.profile_image);
        phone = rootView.findViewById(R.id.phonePro);
        rating = rootView.findViewById(R.id.ratingPro);
        ratingUp = rootView.findViewById(R.id.ratingUp);
        if(check == 1) ratingUp.setVisibility(View.GONE);
        else ratingUp.setVisibility(View.VISIBLE);
        name = rootView.findViewById(R.id.fullname);
        if(check == 1) {
            profile_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getContent();
                }
            });
        }
        ratingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vote();
            }
        });
        showProfile();
        return rootView;
    }

    private void getContent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_SOMETHING);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == PICK_SOMETHING) {
                uri = data.getData();
                upload(uri);
            }
        }
    }


    private void upload(final Uri file) {
        StorageReference referent = storageReference.child("member/"+username);
        final DatabaseReference memberDr = mUsersRef.child(username);

        referent.putFile(file).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(getActivity(),"Download "+taskSnapshot.getDownloadUrl(),Toast.LENGTH_SHORT).show();
                Log.i("onSuccess", "Download : " + taskSnapshot.getDownloadUrl() );
                try {
                    member.setUrl(taskSnapshot.getDownloadUrl().toString());
                    memberDr.child("url").setValue(taskSnapshot.getDownloadUrl().toString());
                    Bitmap bm = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), file);
                    profile_image.setImageBitmap(bm);
                } catch (Exception e){

                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("onFailure",e.toString());
                Toast.makeText(getActivity(),e.toString(),Toast.LENGTH_LONG).show();
            }
        });

    }

    private void showProfile(){
        //Picasso.with(getContext()).load(member.getUrl()).fit().into(profile_image);
        if(!member.getUrl().equals("new")) Glide.with(getContext()).load(member.getUrl()).into(profile_image);
        name.setText(member.getFullname());
        phone.setText(member.getPhone());
        rating.setText(member.getRating());


    }

    private void vote(){


        AlertDialog.Builder builder =
                new AlertDialog.Builder(getContext());
        builder.setMessage("ํYou want to vote this user?");
        builder.setPositiveButton("Vote", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                mUsersRef.child(username).child("rating").setValue(String.valueOf(Integer.valueOf(member.getRating())+1));
                Toast.makeText(getContext(), "Vote Complete!", Toast.LENGTH_SHORT).show();
                member.setRating(String.valueOf(Integer.valueOf(member.getRating())+1));
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
