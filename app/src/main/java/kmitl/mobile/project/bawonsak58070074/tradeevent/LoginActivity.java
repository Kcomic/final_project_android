package kmitl.mobile.project.bawonsak58070074.tradeevent;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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

import kmitl.mobile.project.bawonsak58070074.tradeevent.model.Member;

public class LoginActivity extends AnimateIntent {
    EditText usernameEdt, passwordEdt;
    DatabaseReference mRootRef;
    Button loginBtn;
    private boolean error = false;
    private String errorMessage = null;
    private boolean errorRequied = false;
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameEdt = findViewById(R.id.username);
        passwordEdt = findViewById(R.id.password);
        mRootRef = FirebaseDatabase.getInstance().getReference();
        loginBtn = findViewById(R.id.loginBtn);
        spinner = (ProgressBar)findViewById(R.id.progressBar1);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
                check();
            }
        });
    }

    public void login() {
        final String username = usernameEdt.getText().toString();
        final String password = passwordEdt.getText().toString();
        if(username == null || username.equals("") || password == null || password.equals("")){
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

                if(user == null){
                    error = true;
                    errorMessage = "Username is incorrect";
                    return;
                }
                if(!(password.equals(user.get("password").toString()))){
                    error = true;
                    errorMessage = "Password is incorrect";
                    return;
                }
                error = false;
                Member member = new Member(username, user.get("email").toString(), user.get("rating").toString(), user.get("phone").toString(), user.get("fullname").toString(), user.get("nickname").toString());
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                //Log.e(TAG, databaseError.getMessage());
                System.out.println("error");
            }
        });
    }
    private void check(){

        if(errorRequied == true){
            Toast.makeText(this,errorMessage,Toast.LENGTH_SHORT).show();
        } else {
            spinner.setVisibility(View.VISIBLE);
            Handler myHandler = new Handler();
            myHandler.postDelayed(mMyRunnable, 2000);
        }
    }

    private Runnable mMyRunnable = new Runnable()
    {
        @Override
        public void run()
        {
            if (error == true) {
                spinner.setVisibility(View.GONE);
                Toast.makeText(LoginActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                spinner.setVisibility(View.GONE);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        }
    };

    public void signUp(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
}

