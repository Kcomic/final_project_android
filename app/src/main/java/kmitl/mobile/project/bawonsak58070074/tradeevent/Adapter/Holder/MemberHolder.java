package kmitl.mobile.project.bawonsak58070074.tradeevent.Adapter.Holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import kmitl.mobile.project.bawonsak58070074.tradeevent.R;

/**
 * Created by student on 24/11/2017 AD.
 */

public class MemberHolder extends RecyclerView.ViewHolder {

    public ImageView profile_image;
    public TextView name, phone, rating;

    public MemberHolder(View itemView) {
        super(itemView);
        profile_image = itemView.findViewById(R.id.profile_imageIt);
        name = itemView.findViewById(R.id.nameIt);
        phone = itemView.findViewById(R.id.phoneIt);
        rating = itemView.findViewById(R.id.ratingIt);
    }
}
