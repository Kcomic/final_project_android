package kmitl.mobile.project.bawonsak58070074.tradeevent.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import kmitl.mobile.project.bawonsak58070074.tradeevent.Adapter.MemberSmallAdapter;
import kmitl.mobile.project.bawonsak58070074.tradeevent.R;
import kmitl.mobile.project.bawonsak58070074.tradeevent.model.Event;
import kmitl.mobile.project.bawonsak58070074.tradeevent.model.Member;

public class EventDetailActivity extends AppCompatActivity implements View.OnClickListener, MemberSmallAdapter.MemberAdapterListener {

    private ImageView eventImageDec, shareBtnDec, wantGoPic, wantBuyPic;
    private TextView eventDateDec,eventNameDec, eventLocationDec, eventTimeDec, detail;
    private FragmentManager fragmentManager;
    private LinearLayout whoGo, whoBuy, wantGo, wantBuy;
    private Event event;
    private RecyclerView recyclerView;
    private DatabaseReference mRootRef, mEventRef;
    private MemberSmallAdapter memberAdapter;
    private List<Member> members;
    private Member member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        fragmentManager = getSupportFragmentManager();
        setup();
        mEventRef = mRootRef.child("event");
        String s = event.getDate().split("-")[0]+"\\n-\\n"+event.getDate().split("-")[1];
        s = s.replace("\\n", System.getProperty("line.separator"));
        s = s.replace(" ", "");
        eventDateDec.setText(s);
        eventNameDec.setText(event.getName());
        eventLocationDec.setText(event.getLocation());
        eventTimeDec.setText(event.getTime());
        detail.setText(event.getDetail());
        whoGo.setOnClickListener(this);
        whoBuy.setOnClickListener(this);
        wantGo.setOnClickListener(this);
        wantBuy.setOnClickListener(this);
        for(String toGo : event.getToGo()){
            if(member.getUsername().equals(toGo)){
                wantGoPic.setImageResource(R.drawable.ic_correct);
                wantGo.setOnClickListener(null);
            }
        } for(String toBuy : event.getToBuy()){
            if(member.getUsername().equals(toBuy)){
                wantBuyPic.setImageResource(R.drawable.ic_correct);
                wantBuy.setOnClickListener(null);
            }
        }

        Glide.with(getApplicationContext())
                .load(event.getUrl())
                .into(eventImageDec);

        shareBtnDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, event.getLinkShare());
                intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Check out this site!");
                startActivity(Intent.createChooser(intent, "Share to"));
            }
        });

    }
    private void setup(){
        event = getIntent().getParcelableExtra("event");
        member = getIntent().getParcelableExtra("member");
        mRootRef = FirebaseDatabase.getInstance().getReference();
        members = new ArrayList<>();
        eventImageDec = findViewById(R.id.eventImageDec);
        eventDateDec = findViewById(R.id.eventDateDec);
        eventNameDec = findViewById(R.id.eventNameDec);
        eventLocationDec = findViewById(R.id.eventLocationDec);
        eventTimeDec = findViewById(R.id.eventTimeDec);
        detail = findViewById(R.id.detail);
        shareBtnDec = findViewById(R.id.shareBtnDec);
        whoGo = findViewById(R.id.whoGo);
        whoBuy = findViewById(R.id.whoBuy);
        wantGo = findViewById(R.id.wantGo);
        wantBuy = findViewById(R.id.wantBuy);
        wantGoPic = findViewById(R.id.wantGoPic);
        wantBuyPic = findViewById(R.id.wantBuyPic);
        recyclerView = findViewById(R.id.memberListDec);
    }

    @Override
    public void onClick(View view) {
        if(R.id.whoGo == view.getId()){
            whoGo.setBackgroundColor(Color.parseColor("#a6aaea"));
            whoBuy.setBackgroundColor(Color.parseColor("#f9f6f6"));
            members = new ArrayList<>();
            query(event.getToGo());
            whoGo.setOnClickListener(null);
            whoBuy.setOnClickListener(this);
        } else if(R.id.whoBuy == view.getId()) {
            whoGo.setBackgroundColor(Color.parseColor("#f9f6f6"));
            whoBuy.setBackgroundColor(Color.parseColor("#a6aaea"));
            members = new ArrayList<>();
            query(event.getToBuy());
            whoGo.setOnClickListener(this);
            whoBuy.setOnClickListener(null);
        } else if(R.id.wantGo == view.getId()){
            final DatabaseReference eventRef = mEventRef.child(event.getRealName());
            AlertDialog.Builder builder =
                    new AlertDialog.Builder(this);
            builder.setMessage("You want to go?");
            builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                }
            });
            builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    eventRef.child("toGo").child(String.valueOf(event.getToGo().size())).setValue(member.getUsername());
                    Toast.makeText(getApplicationContext(), "Check in Complete!", Toast.LENGTH_SHORT).show();
                    event.addToGo(member.getUsername());
                    wantGo.setOnClickListener(null);
                    wantGoPic.setImageResource(R.drawable.ic_correct);
                }
            });
            builder.show();

        } else if(R.id.wantBuy == view.getId()){

            final DatabaseReference eventRef = mEventRef.child(event.getRealName());
            AlertDialog.Builder builder =
                    new AlertDialog.Builder(this);
            builder.setMessage("You want to Buy?");
            builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                }
            });
            builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    eventRef.child("toBuy").child(String.valueOf(event.getToBuy().size())).setValue(member.getUsername());
                    Toast.makeText(getApplicationContext(), "Check in Complete!", Toast.LENGTH_SHORT).show();
                    event.addToBuy(member.getUsername());
                    wantBuy.setOnClickListener(null);
                    wantBuyPic.setImageResource(R.drawable.ic_correct);
                }
            });
            builder.show();

        }
    }

    @Override
    public void onItemTouched(Member member) {
        if(!this.member.getUsername().equals(member.getUsername())) {
            Intent intent = new Intent(this, ProfileSomeone.class);
            intent.putExtra("member", member);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    }

    public void query(final List<String> who){
        mRootRef.child("member").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Object> memberList = (Map<String, Object>) dataSnapshot.getValue();

                for(String key : memberList.keySet()){
                    Map<String, Object> m = (Map<String, Object>) memberList.get(key);
                    for(String w : who) {
                        if (m.get("username").toString().equals(w)) {
                            Member e = new Member(m.get("username").toString(), m.get("email").toString(), m.get("rating").toString(), m.get("phone").toString(), m.get("fullname").toString(), m.get("nickname").toString(), m.get("url").toString());
                            members.add(e);
                        }
                    }

                }

                members = sortRating();

                memberAdapter = new MemberSmallAdapter(EventDetailActivity.this, EventDetailActivity.this);
                recyclerView.setAdapter(memberAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(EventDetailActivity.this));
                memberAdapter.setMembers(members);
                memberAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }

    private List<Member> sortRating(){

        int[] ratings = new int[members.size()];
        int i = 0;
        for(Member member : members){
            ratings[i] = Integer.valueOf(member.getRating());
            i++;
        }
        Arrays.sort(ratings);
        List<Member> memberRatingSorted = new ArrayList<>();
        List<Member> memberCompare = members;
        for(int y = ratings.length-1; y>= 0;y--){
            for(int z = 0; z<members.size();z++){
                if(ratings[y] == Integer.valueOf(memberCompare.get(z).getRating())){
                    memberRatingSorted.add(memberCompare.remove(z));
                }
            }
        }
        return memberRatingSorted;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            Log.d("CDA", "onKeyDown Called");
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        Intent intent = new Intent(EventDetailActivity.this, MainActivity.class);
        intent.putExtra("member", member);
        startActivity(intent);
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

}
