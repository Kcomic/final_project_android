package kmitl.mobile.project.bawonsak58070074.tradeevent.controller.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import kmitl.mobile.project.bawonsak58070074.tradeevent.Adapter.EventAdapter;
import kmitl.mobile.project.bawonsak58070074.tradeevent.R;
import kmitl.mobile.project.bawonsak58070074.tradeevent.controller.Activity.EventDetailActivity;
import kmitl.mobile.project.bawonsak58070074.tradeevent.model.Event;
import kmitl.mobile.project.bawonsak58070074.tradeevent.model.Member;

public class HomeFragment extends Fragment implements EventAdapter.EventAdapterListener {

    private EventAdapter eventAdapter;
    private DatabaseReference mRootRef;
    private List<Event> events;
    private RecyclerView recyclerView;
    private Member member;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(Member member) {

        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        args.putParcelable("member", member);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRootRef = FirebaseDatabase.getInstance().getReference();
        events = new ArrayList<>();
        member = getArguments().getParcelable("member");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = rootView.findViewById(R.id.homeList);
        query();
        return rootView;
    }

    @Override
    public void onItemTouched(Event event) {
        Intent intent = new Intent(getActivity(), EventDetailActivity.class);
        intent.putExtra("event", event);
        intent.putExtra("member", member);
        startActivity(intent);
        getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

    }

    public void query(){
        mRootRef.child("event").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Object> eventList = (Map<String, Object>) dataSnapshot.getValue();

                for(String key : eventList.keySet()){
                    Map<String, Object> m = (Map<String, Object>) eventList.get(key);
                    Event e = new Event(key, m.get("name").toString(), m.get("url").toString(), m.get("detail").toString(), m.get("date").toString(), m.get("time").toString(), m.get("location").toString(), m.get("type").toString(), m.get("fulldate").toString(), m.get("linkShare").toString());
                    List<String> toGo = (List<String>) m.get("toGo");
                    List<String> toBuy = (List<String>) m.get("toBuy");
                    e.setToBuy(toBuy);
                    e.setToGo(toGo);
                    if(compareTime(e.getFulldate())) events.add(e);
                }
                eventAdapter = new EventAdapter(getActivity(), HomeFragment.this);
                recyclerView.setAdapter(eventAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                eventAdapter.setEvents(events);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }

    private boolean compareTime(String dateEvent){
        Date dateNow = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return dateFormat.parse(dateEvent).before(dateNow);
        } catch (ParseException e) {

        }
        return false;
    }

}
