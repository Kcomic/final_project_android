package kmitl.mobile.project.bawonsak58070074.tradeevent.Adapter.Holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import kmitl.mobile.project.bawonsak58070074.tradeevent.R;

/**
 * Created by student on 23/11/2017 AD.
 */

public class EventHolder extends RecyclerView.ViewHolder  {

    public ImageView eventImage;
    public TextView eventTime, eventDate, eventLocation, eventName;

    public EventHolder(View itemView) {
        super(itemView);
        eventImage = itemView.findViewById(R.id.eventImage);
        eventDate = itemView.findViewById(R.id.eventDate);
        eventTime = itemView.findViewById(R.id.eventTime);
        eventLocation = itemView.findViewById(R.id.eventLocation);
        eventName = itemView.findViewById(R.id.eventName);
    }

}
