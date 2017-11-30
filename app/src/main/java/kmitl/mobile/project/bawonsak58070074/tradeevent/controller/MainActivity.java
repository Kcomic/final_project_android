package kmitl.mobile.project.bawonsak58070074.tradeevent.controller;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import kmitl.mobile.project.bawonsak58070074.tradeevent.R;
import kmitl.mobile.project.bawonsak58070074.tradeevent.model.Member;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout homeBtn, eventBtn, ratingBtn, profileBtn;
    private TextView homeTv, eventTv, ratingTv, profileTv;
    private ImageView homeIv, eventIv , ratingIv, profileIv;
    private ImageView profile_image;
    FragmentManager fragmentManager;
    Member member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setup();
        Reset();
        fragmentManager = getSupportFragmentManager();
        homeTv.setTextColor(Color.parseColor("#ff4656"));
        homeIv.setImageResource(R.drawable.ic_press_home);
        Intent intent = getIntent();
        member = intent.getParcelableExtra("member");
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, new HomeFragment().newInstance(member))
                .commit();
    }
    private void setup(){

        homeBtn = findViewById(R.id.homeBtn);
        eventBtn = findViewById(R.id.eventBtn);
        ratingBtn = findViewById(R.id.ratingBtn);
        profileBtn = findViewById(R.id.profileBtn);

        homeTv = findViewById(R.id.homeTv);
        eventTv = findViewById(R.id.eventTv);
        ratingTv = findViewById(R.id.ratingTv);
        profileTv = findViewById(R.id.profileTv);

        homeIv = findViewById(R.id.homeIv);
        eventIv = findViewById(R.id.eventIv);
        ratingIv = findViewById(R.id.ratingIv);
        profileIv = findViewById(R.id.profileIv);

        profile_image = findViewById(R.id.profile_image);

    }
    @Override
    public void onClick(View view) {
        if(R.id.homeBtn == view.getId()){
            Reset();
            homeBtn.setOnClickListener(null);
            homeTv.setTextColor(Color.parseColor("#ff4656"));
            homeIv.setImageResource(R.drawable.ic_press_home);
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, new HomeFragment().newInstance(member))
                    .commit();
        } else if (R.id.eventBtn == view.getId()){
            Reset();
            eventBtn.setOnClickListener(null);
            eventTv.setTextColor(Color.parseColor("#ff4656"));
            eventIv.setImageResource(R.drawable.ic_press_time);
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, new EventFragment().newInstance(member))
                    .commit();
        } else if (R.id.ratingBtn == view.getId()){
            Reset();
            ratingBtn.setOnClickListener(null);
            ratingTv.setTextColor(Color.parseColor("#ff4656"));
            ratingIv.setImageResource(R.drawable.ic_press_rating);
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, new RatingFragment().newInstance(member))
                    .commit();
        } else if (R.id.profileBtn == view.getId()){
            Reset();
            profileBtn.setOnClickListener(null);
            profileTv.setTextColor(Color.parseColor("#ff4656"));
            profileIv.setImageResource(R.drawable.ic_press_user);
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, new ProfileFragment().newInstance(member))
                    .commit();
        }
    }
    private void Reset(){
        homeTv.setTextColor(Color.parseColor("#727272"));
        eventTv.setTextColor(Color.parseColor("#727272"));
        ratingTv.setTextColor(Color.parseColor("#727272"));
        profileTv.setTextColor(Color.parseColor("#727272"));
        homeIv.setImageResource(R.drawable.ic_menu_home);
        eventIv.setImageResource(R.drawable.ic_menu_time);
        ratingIv.setImageResource(R.drawable.ic_menu_rating);
        profileIv.setImageResource(R.drawable.ic_menu_user);
        homeBtn.setOnClickListener(this);
        eventBtn.setOnClickListener(this);
        ratingBtn.setOnClickListener(this);
        profileBtn.setOnClickListener(this);

    }
}
