package kmitl.mobile.project.bawonsak58070074.tradeevent.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import kmitl.mobile.project.bawonsak58070074.tradeevent.Adapter.Holder.EventHolder;
import kmitl.mobile.project.bawonsak58070074.tradeevent.Adapter.Holder.MemberHolder;
import kmitl.mobile.project.bawonsak58070074.tradeevent.R;
import kmitl.mobile.project.bawonsak58070074.tradeevent.model.Member;

/**
 * Created by student on 24/11/2017 AD.
 */

public class MemberAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<Member> members;
    private MemberAdapter.MemberAdapterListener listener;
    public MemberAdapter(Context mContext, MemberAdapter.MemberAdapterListener listener){
        this.mContext = mContext;
        this.listener = listener;
        members = new ArrayList<>();
    }
    public interface MemberAdapterListener {

        public void onItemTouched(Member member);
    }
    public void setMembers(List<Member> members) {
        this.members = members;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_member, parent, false);
        MemberHolder memberHolder = new MemberHolder(view);

        return memberHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((MemberHolder)holder).itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemTouched(members.get(position));
            }
        });
        ((MemberHolder)holder).name.setText(members.get(position).getFullname());
        ((MemberHolder)holder).phone.setText(members.get(position).getPhone());
        ((MemberHolder)holder).rating.setText(members.get(position).getRating());
        if(!members.get(position).getUrl().equals("new")) {
            Log.i("Kcomiccczzzzzzz", position+" : "+members.get(position).getUrl());
            Glide.with(mContext)
                    .load(members.get(position).getUrl())
                    .into(((MemberHolder) holder).profile_image);
        }
    }

    @Override
    public int getItemCount() {
        return members.size();
    }
}
