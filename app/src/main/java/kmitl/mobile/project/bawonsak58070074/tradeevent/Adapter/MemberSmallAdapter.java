package kmitl.mobile.project.bawonsak58070074.tradeevent.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import kmitl.mobile.project.bawonsak58070074.tradeevent.Adapter.Holder.MemberHolder;
import kmitl.mobile.project.bawonsak58070074.tradeevent.Adapter.Holder.MemberSmallHolder;
import kmitl.mobile.project.bawonsak58070074.tradeevent.R;
import kmitl.mobile.project.bawonsak58070074.tradeevent.model.Member;

/**
 * Created by student on 24/11/2017 AD.
 */

public class MemberSmallAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<Member> members;
    private MemberSmallAdapter.MemberAdapterListener listener;
    public MemberSmallAdapter(Context mContext, MemberSmallAdapter.MemberAdapterListener listener){
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
        View view = inflater.inflate(R.layout.item_member_small, parent, false);
        MemberSmallHolder memberHolder = new MemberSmallHolder(view);

        return memberHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((MemberSmallHolder)holder).itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemTouched(members.get(position));
            }
        });
        ((MemberSmallHolder)holder).name.setText(members.get(position).getFullname());
        ((MemberSmallHolder)holder).phone.setText(members.get(position).getPhone());
        ((MemberSmallHolder)holder).rating.setText(members.get(position).getRating());
        if(!members.get(position).getUrl().equals("new")) {
            Glide.with(mContext)
                    .load(members.get(position).getUrl())
                    .into(((MemberSmallHolder) holder).profile_image);
        }
    }

    @Override
    public int getItemCount() {
        return members.size();
    }
}
