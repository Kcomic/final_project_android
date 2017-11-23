package kmitl.mobile.project.bawonsak58070074.tradeevent;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kmitl.mobile.project.bawonsak58070074.tradeevent.Adapter.EventAdapter;
import kmitl.mobile.project.bawonsak58070074.tradeevent.model.Event;
import kmitl.mobile.project.bawonsak58070074.tradeevent.model.Events;


public class EventFragment extends Fragment implements EventAdapter.EventAdapterListener {

    private EventAdapter eventAdapter;
    private DatabaseReference mRootRef;
    private List<Event> events;
    private RecyclerView recyclerView;

    public EventFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
//    public static EventFragment newInstance(String param1, String param2) {
//        EventFragment fragment = new EventFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRootRef = FirebaseDatabase.getInstance().getReference();
        events = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_event, container, false);
        recyclerView = rootView.findViewById(R.id.eventList);
        query();
        return rootView;
    }

    @Override
    public void onItemTouched(Event event) {

    }

    public void query(){
        mRootRef.child("event").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Object> eventList = (Map<String, Object>) dataSnapshot.getValue();

                for(String key : eventList.keySet()){
                    Map<String, Object> m = (Map<String, Object>) eventList.get(key);
                    Event e = new Event(m.get("name").toString(), m.get("url").toString(), m.get("detail").toString(), m.get("date").toString(), m.get("time").toString(), m.get("location").toString(), m.get("type").toString(), m.get("fulldate").toString(), m.get("linkShare").toString());
                    List<String> toGo = (List<String>) m.get("toGo");
                    List<String> toBuy = (List<String>) m.get("toBuy");
                    e.setToBuy(toBuy);
                    e.setToGo(toGo);
                    events.add(e);
                }
                eventAdapter = new EventAdapter(getActivity(), EventFragment.this);
                recyclerView.setAdapter(eventAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                eventAdapter.setEvents(events);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }

}
