package kmitl.mobile.project.bawonsak58070074.tradeevent;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

import kmitl.mobile.project.bawonsak58070074.tradeevent.model.Member;

import static android.app.Activity.RESULT_OK;

public class ProfileFragment extends Fragment {

    private static final int PICK_SOMETHING = 1;
    private StorageReference storageReference;
    private TextView text;
    private Button button;
    private Uri uri;
    private Member member;
    ImageView profile_image;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(Member member) {
        Bundle args = new Bundle();
        ProfileFragment fragment = new ProfileFragment();
        args.putParcelable("member", member);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        storageReference = FirebaseStorage.getInstance().getReference();
        member = getArguments().getParcelable("member");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);



        profile_image = rootView.findViewById(R.id.profile_image);
        button = rootView.findViewById(R.id.btn_upload);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getContent();
            }
        });
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
        StorageReference referent = storageReference.child("member/"+member.getUsername());


        referent.putFile(file).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(getActivity(),"Download "+taskSnapshot.getDownloadUrl(),Toast.LENGTH_SHORT).show();
                Log.i("onSuccess", "Download : " + taskSnapshot.getDownloadUrl() );
                try {


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

}
