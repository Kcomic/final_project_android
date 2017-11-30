package kmitl.mobile.project.bawonsak58070074.tradeevent.controller.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import kmitl.mobile.project.bawonsak58070074.tradeevent.R;
import kmitl.mobile.project.bawonsak58070074.tradeevent.model.Member;

public class PhoneActivity extends AppCompatActivity {
    EditText phone;
    Member member;
    Button loginBtnPhone;
    private DatabaseReference mRootRef, mUsersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        mRootRef = FirebaseDatabase.getInstance().getReference();
        mUsersRef = mRootRef.child("member");
        phone = findViewById(R.id.new_phone_facebook);
        loginBtnPhone = findViewById(R.id.loginBtnPhone);
        member = getIntent().getParcelableExtra("member");
        loginBtnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                member.setPhone(PhoneNumberUtils.formatNumber(phone.getText().toString()));
                DatabaseReference memberRef = mUsersRef.child(member.getUsername());
                memberRef.child("username").setValue(member.getUsername());
                memberRef.child("password").setValue("null");
                memberRef.child("email").setValue(member.getUsername());
                memberRef.child("phone").setValue(member.getPhone());
                memberRef.child("rating").setValue(member.getRating());
                memberRef.child("fullname").setValue(member.getFullname());
                memberRef.child("nickname").setValue(member.getNickname());
                memberRef.child("url").setValue(member.getUrl());
                Intent intent = new Intent(PhoneActivity.this, MainActivity.class);
                intent.putExtra("member", member);
                startActivity(intent);
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

            }
        });

    }
}
