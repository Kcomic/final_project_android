package kmitl.mobile.project.bawonsak58070074.tradeevent.Adapter.Holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import kmitl.mobile.project.bawonsak58070074.tradeevent.R;

/**
 * Created by student on 24/11/2017 AD.
 */

public class MemberSmallHolder extends RecyclerView.ViewHolder {

    public ImageView profile_image;
    public TextView name, phone, rating;

    public MemberSmallHolder(View itemView) {
        super(itemView);
        profile_image = itemView.findViewById(R.id.profile_imageIts);
        name = itemView.findViewById(R.id.nameIts);
        phone = itemView.findViewById(R.id.phoneIts);
        rating = itemView.findViewById(R.id.ratingIts);
    }
}
