package kmitl.mobile.project.bawonsak58070074.tradeevent;


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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kmitl.mobile.project.bawonsak58070074.tradeevent.Adapter.EventAdapter;
import kmitl.mobile.project.bawonsak58070074.tradeevent.Adapter.MemberAdapter;
import kmitl.mobile.project.bawonsak58070074.tradeevent.model.Event;
import kmitl.mobile.project.bawonsak58070074.tradeevent.model.Member;


public class RatingFragment extends Fragment implements MemberAdapter.MemberAdapterListener {

    private MemberAdapter memberAdapter;
    private DatabaseReference mRootRef;
    private List<Member> members;
    private RecyclerView recyclerView;

    public RatingFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRootRef = FirebaseDatabase.getInstance().getReference();
        members = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_rating, container, false);
        recyclerView = rootView.findViewById(R.id.memberList);
        query();
        return rootView;
    }

    @Override
    public void onItemTouched(Member member) {

    }

    public void query(){
        mRootRef.child("member").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Object> memberList = (Map<String, Object>) dataSnapshot.getValue();

                for(String key : memberList.keySet()){
                    Map<String, Object> m = (Map<String, Object>) memberList.get(key);
                    Member e = new Member(m.get("username").toString(), m.get("email").toString(), m.get("rating").toString(), m.get("phone").toString(), m.get("fullname").toString(), m.get("nickname").toString(), m.get("url").toString());
                    members.add(e);
                }
                memberAdapter = new MemberAdapter(getActivity(), RatingFragment.this);
                recyclerView.setAdapter(memberAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                memberAdapter.setMembers(members);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }

}
