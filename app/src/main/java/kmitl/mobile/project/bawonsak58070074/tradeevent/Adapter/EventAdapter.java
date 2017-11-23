package kmitl.mobile.project.bawonsak58070074.tradeevent.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import kmitl.mobile.project.bawonsak58070074.tradeevent.Adapter.Holder.EventHolder;
import kmitl.mobile.project.bawonsak58070074.tradeevent.R;
import kmitl.mobile.project.bawonsak58070074.tradeevent.model.Event;

/**
 * Created by student on 23/11/2017 AD.
 */

public class EventAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

private Context mContext;
private List<Event> events;
private EventAdapterListener listener;
public EventAdapter(Context mContext, EventAdapterListener listener){
        this.mContext = mContext;
        this.listener = listener;
        events = new ArrayList<>();
        }
public interface EventAdapterListener {

    public void onItemTouched(Event event);
}
    public void setEvents(List<Event> events) {
        this.events = events;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_event, parent, false);
        EventHolder eventHolder = new EventHolder(view);

        return eventHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((EventHolder)holder).itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemTouched(events.get(position));
            }
        });
        ((EventHolder)holder).eventDate.setText(events.get(position).getDate());
        ((EventHolder)holder).eventName.setText(events.get(position).getName());
        ((EventHolder)holder).eventLocation.setText(events.get(position).getLocation());
        ((EventHolder)holder).eventTime.setText(events.get(position).getTime());
        //Picasso.with(mContext).load(events.get(position).getUrl()).fit().into(((EventHolder)holder).eventImage);
        Glide.with(mContext)
                .load(events.get(position).getUrl())
                .into(((EventHolder)holder).eventImage);

    }

    @Override
    public int getItemCount() {
        return events.size();
    }
}
