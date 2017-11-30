package kmitl.mobile.project.bawonsak58070074.tradeevent.controller;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;

import kmitl.mobile.project.bawonsak58070074.tradeevent.AnimateIntent;
import kmitl.mobile.project.bawonsak58070074.tradeevent.R;
import kmitl.mobile.project.bawonsak58070074.tradeevent.UserPreferences;
import kmitl.mobile.project.bawonsak58070074.tradeevent.model.Member;

public class LoginActivity extends AnimateIntent {
    private EditText usernameEdt, passwordEdt;
    private DatabaseReference mRootRef;
    private Button loginBtn;
    private boolean error = false;
    private String errorMessage = null;
    private boolean errorRequied = false;
    private Member member;
    private LoginButton loginButton;
    private AccessToken accessToken;
    private CallbackManager callbackManager;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.activity_login);
        usernameEdt = findViewById(R.id.username);
        passwordEdt = findViewById(R.id.password);
        mRootRef = FirebaseDatabase.getInstance().getReference();
        loginBtn = findViewById(R.id.loginBtn);
        if(UserPreferences.getUserName(this).length() != 0)
        {
            autoLogin();
        }
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        loginButton = findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");
        loginButton.setReadPermissions(Arrays.asList("user_status"));
        callbackManager = CallbackManager.Factory.create();
        accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken != null) {
            LoginFacebook();
        }
        // Callback registration
        loginButton.registerCallback(this.callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.i("Facebook", "Success!!");
                LoginFacebook();
            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException exception) {
            }
        });

    }

    public void login() {
        final String username = usernameEdt.getText().toString();
        final String password = passwordEdt.getText().toString();
        if (username == null || username.equals("") || password == null || password.equals("")) {
            error = true;
            errorRequied = true;
        } else errorRequied = false;
        progress = new ProgressDialog(LoginActivity.this);
        progress.setMessage("login..");

        mRootRef.child("member").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Object> userList = (Map<String, Object>) dataSnapshot.getValue();
                Map<String, Object> user = (Map<String, Object>) userList.get(username);

                if (user == null) {
                    error = true;
                    errorMessage = "Username is incorrect";
                } else if (!(password.equals(user.get("password").toString()))) {
                    error = true;
                    errorMessage = "Password is incorrect";
                } else {
                    error = false;
                    UserPreferences.setUserName(getApplicationContext(), username);
                    member = new Member(username, user.get("email").toString(), user.get("rating").toString(), user.get("phone").toString(), user.get("fullname").toString(), user.get("nickname").toString(), user.get("url").toString());
                }
                if(errorRequied ){
                    errorMessage = "Please complete informations";
                }
                check();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //Log.e(TAG, databaseError.getMessage());
                System.out.println("error");
            }
        });

    }

    private void check() {

        if (errorRequied) {
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
        } else {
            if (error) {
                Toast.makeText(LoginActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            } else {
                progress.show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("member", member);
                startActivity(intent);
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        }
    }

    public void signUp(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    private void LoginFacebook() {
        boolean isError = false;
//        try {
            final Profile profile = Profile.getCurrentProfile();
            Log.i("Facebook login", profile.getName());
            Log.i("Facebook login", profile.getId());
            Log.i("Facebook login", profile.getFirstName());
            mRootRef.child("member").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Map<String, Object> userList = (Map<String, Object>) dataSnapshot.getValue();
                    Map<String, Object> user = (Map<String, Object>) userList.get(profile.getId());
                    if (user == null) {
                        member = new Member(profile.getId(), profile.getId(), "0", profile.getName(), profile.getFirstName(), profile.getProfilePictureUri(100, 100).toString());
                        Intent intent = new Intent(LoginActivity.this, PhoneActivity.class);
                        intent.putExtra("member", member);
                        startActivity(intent);
                        finish();
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    } else {
                        member = new Member(profile.getId(), profile.getId(), user.get("rating").toString(), user.get("phone").toString(), profile.getName(), profile.getFirstName(), profile.getProfilePictureUri(100, 100).toString());
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("member", member);
                        startActivity(intent);
                        finish();
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

//        } catch (Exception ex) {
//
//            Toast.makeText(LoginActivity.this, "Please login with facebook again", Toast.LENGTH_SHORT).show();
//            isError = true;
//        }
//        if (isError) {
//            LoginManager.getInstance().logOut();
//        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void autoLogin(){
        final String username = UserPreferences.getUserName(this);
        progress = new ProgressDialog(LoginActivity.this);
        progress.setMessage("login..");
        mRootRef.child("member").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Object> userList = (Map<String, Object>) dataSnapshot.getValue();
                Map<String, Object> user = (Map<String, Object>) userList.get(username);
                member = new Member(username, user.get("email").toString(), user.get("rating").toString(), user.get("phone").toString(), user.get("fullname").toString(), user.get("nickname").toString(), user.get("url").toString());
                progress.show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("member", member);
                UserPreferences.setUserName(getApplicationContext(), username);
                startActivity(intent);
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //Log.e(TAG, databaseError.getMessage());
                System.out.println("error");
            }
        });

    }

}

