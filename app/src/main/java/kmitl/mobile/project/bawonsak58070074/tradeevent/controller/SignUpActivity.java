package kmitl.mobile.project.bawonsak58070074.tradeevent.controller;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

import kmitl.mobile.project.bawonsak58070074.tradeevent.AnimateIntent;
import kmitl.mobile.project.bawonsak58070074.tradeevent.InsertMember;
import kmitl.mobile.project.bawonsak58070074.tradeevent.R;
import kmitl.mobile.project.bawonsak58070074.tradeevent.model.Member;

public class SignUpActivity extends AnimateIntent implements InsertMember {
    private EditText fullnameEt, nicknameEt, usernameEt, passwordEt, emailEt, phoneEt;
    private DatabaseReference mRootRef, mUsersRef;
    boolean error = false, errorRequied = true;
    private String errorMessage = null;
    private String fullname, nickname, username, password, email, phone;
    ProgressDialog progress;
    Button signUpBtn;
    Member member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        fullnameEt = findViewById(R.id.new_fullname);
        nicknameEt = findViewById(R.id.new_nickname);
        usernameEt = findViewById(R.id.new_username);
        passwordEt = findViewById(R.id.new_password);
        emailEt = findViewById(R.id.new_email);
        phoneEt = findViewById(R.id.new_phone);
        mRootRef = FirebaseDatabase.getInstance().getReference();
        mUsersRef = mRootRef.child("member");
        signUpBtn = findViewById(R.id.signUpBtn);
        progress = new ProgressDialog(SignUpActivity.this);
        progress.setMessage("loading..");
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });

    }

    public void signUp(){
        fullname = fullnameEt.getText().toString();
        nickname = nicknameEt.getText().toString();
        username = usernameEt.getText().toString();
        password = passwordEt.getText().toString();
        email = emailEt.getText().toString();
        phone = PhoneNumberUtils.formatNumber(phoneEt.getText().toString());

        if(username == null || username.equals("") || password == null || password.equals("") || fullname == null || fullname.equals("") ||
                nickname == null || nickname.equals("") || email == null || email.equals("") || phone == null || phone.equals("")
                ){
            error = true;
            errorRequied = true;
            errorMessage = "Please complete informations";
            return;
        }
        errorRequied = false;
        mRootRef.child("member").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Object> userList = (Map<String, Object>) dataSnapshot.getValue();
                Map<String, Object> user = (Map<String, Object>) userList.get(username);

                if(user != null){
                    error = true;
                    errorMessage = "Username is already used";
                } else {
                    error = false;
                    insert();
                    member = new Member(username, email, "0", phone, fullname, nickname, "https://firebasestorage.googleapis.com/v0/b/trade-event.appspot.com/o/member%2Fprofile-icon-9%20(1).png?alt=media&token=2fd99d01-1b36-4377-b9ba-6113b0882a41");
                }
                check();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                //Log.e(TAG, databaseError.getMessage());
            }
        });
    }
    public void signIn(View view) {
        finish();
//        Intent intent = new Intent(this, LoginActivity.class);
//        startActivity(intent);
    }

    @Override
    public void insert() {
        DatabaseReference member = mUsersRef.child(username);
        member.child("username").setValue(username);
        member.child("password").setValue(password);
        member.child("email").setValue(email);
        member.child("phone").setValue(phone);
        member.child("rating").setValue("0");
        member.child("fullname").setValue(fullname);
        member.child("nickname").setValue(nickname);
        member.child("url").setValue("https://firebasestorage.googleapis.com/v0/b/trade-event.appspot.com/o/member%2Fprofile-icon-9%20(1).png?alt=media&token=2fd99d01-1b36-4377-b9ba-6113b0882a41");
    }

    public void check(){
        if(errorRequied == true){
            Toast.makeText(this,errorMessage,Toast.LENGTH_SHORT).show();
        } else {
            if (error == true) {
                Toast.makeText(SignUpActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            } else {
                progress.show();
                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                intent.putExtra("member", member);
                startActivity(intent);
                finish();
            }
        }
    }
}
