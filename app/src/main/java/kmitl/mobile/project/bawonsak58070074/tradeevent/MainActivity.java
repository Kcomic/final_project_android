package kmitl.mobile.project.bawonsak58070074.tradeevent;

import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout homeBtn, eventBtn, searchBtn, ratingBtn, profileBtn;
    private TextView homeTv, eventTv, searchTv, ratingTv, profileTv;
    private ImageView homeIv, eventIv, searchIv, ratingIv, profileIv;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setup();
        ResetColor();
        fragmentManager = getSupportFragmentManager();
        homeBtn.setOnClickListener(this);
        eventBtn.setOnClickListener(this);
        searchBtn.setOnClickListener(this);
        ratingBtn.setOnClickListener(this);
        profileBtn.setOnClickListener(this);

        homeTv.setTextColor(Color.parseColor("#ff4656"));
        homeIv.setBackground(getResources().getDrawable(R.drawable.ic_press_home));
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, new HomeFragment())
                .commit();

    }
    private void setup(){

        homeBtn = findViewById(R.id.homeBtn);
        eventBtn = findViewById(R.id.eventBtn);
        searchBtn = findViewById(R.id.SearchBtn);
        ratingBtn = findViewById(R.id.ratingBtn);
        profileBtn = findViewById(R.id.profileBtn);

        homeTv = findViewById(R.id.homeTv);
        eventTv = findViewById(R.id.eventTv);
        searchTv = findViewById(R.id.searchTv);
        ratingTv = findViewById(R.id.ratingTv);
        profileTv = findViewById(R.id.profileTv);

        homeIv = findViewById(R.id.homeIv);
        eventIv = findViewById(R.id.eventIv);
        searchIv = findViewById(R.id.searchIv);
        ratingIv = findViewById(R.id.ratingIv);
        profileIv = findViewById(R.id.profileIv);

    }
    @Override
    public void onClick(View view) {
        if(R.id.homeBtn == view.getId()){
            ResetColor();
            homeTv.setTextColor(Color.parseColor("#ff4656"));
            homeIv.setBackground(getResources().getDrawable(R.drawable.ic_press_home));
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, new HomeFragment())
                    .commit();
        } else if (R.id.eventBtn == view.getId()){
            ResetColor();
            eventTv.setTextColor(Color.parseColor("#ff4656"));
            eventIv.setBackground(getResources().getDrawable(R.drawable.ic_press_event));
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, new EventFragment())
                    .commit();
        } else if (R.id.SearchBtn == view.getId()){
            ResetColor();
            searchTv.setTextColor(Color.parseColor("#ff4656"));
            searchIv.setBackground(getResources().getDrawable(R.drawable.ic_press_search));
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, new SearchFragment())
                    .commit();
        } else if (R.id.ratingBtn == view.getId()){
            ResetColor();
            ratingTv.setTextColor(Color.parseColor("#ff4656"));
            ratingIv.setBackground(getResources().getDrawable(R.drawable.ic_press_rating));
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, new RatingFragment())
                    .commit();
        } else if (R.id.profileBtn == view.getId()){
            ResetColor();
            profileTv.setTextColor(Color.parseColor("#ff4656"));
            profileIv.setBackground(getResources().getDrawable(R.drawable.ic_press_user));
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, new ProfileFragment())
                    .commit();
        }
    }
    private void ResetColor(){
        homeTv.setTextColor(Color.parseColor("#727272"));
        eventTv.setTextColor(Color.parseColor("#727272"));
        searchTv.setTextColor(Color.parseColor("#727272"));
        ratingTv.setTextColor(Color.parseColor("#727272"));
        profileTv.setTextColor(Color.parseColor("#727272"));
        homeIv.setBackground(getResources().getDrawable(R.drawable.ic_menu_home));
        eventIv.setBackground(getResources().getDrawable(R.drawable.ic_action_name));
        searchIv.setBackground(getResources().getDrawable(R.drawable.ic_menu_search));
        ratingIv.setBackground(getResources().getDrawable(R.drawable.ic_menu_rating));
        profileIv.setBackground(getResources().getDrawable(R.drawable.ic_menu_user));
    }
}
