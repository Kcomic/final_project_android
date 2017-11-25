package kmitl.mobile.project.bawonsak58070074.tradeevent.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Kcomic on 21/11/2560.
 */

public class Event implements Parcelable {

    private String name;
    private String url;
    private String detail;
    private String date;
    private String time;
    private String location;
    private String type;
    private String fulldate;
    private String linkShare;
    private String realName;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    private List<String> toBuy = Collections.emptyList(), toGo = Collections.emptyList();

    public Event(String realName, String name, String url, String detail, String date, String time, String location, String type, String fulldate, String linkShare) {
        this.realName = realName;
        this.name = name;
        this.url = url;
        this.detail = detail;
        this.date = date;
        this.time = time;
        this.location = location;
        this.type = type;
        this.fulldate = fulldate;
        this.linkShare = linkShare;
    }

    protected Event(Parcel in) {
        realName = in.readString();
        name = in.readString();
        url = in.readString();
        detail = in.readString();
        date = in.readString();
        time = in.readString();
        location = in.readString();
        type = in.readString();
        fulldate = in.readString();
        linkShare = in.readString();
        toBuy = in.createStringArrayList();
        toGo = in.createStringArrayList();
    }

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    public String getLinkShare() {
        return linkShare;
    }

    public void setLinkShare(String linkShare) {
        this.linkShare = linkShare;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<String> getToBuy() {
        return toBuy;
    }

    public void setToBuy(List toBuy) {
        this.toBuy = toBuy;
    }

    public List<String> getToGo() {
        return toGo;
    }

    public void setToGo(List toGo) {
        this.toGo = toGo;
    }

    public String getFulldate() {
        return fulldate;
    }

    public void setFulldate(String fulldate) {
        this.fulldate = fulldate;
    }
    public void addToGo(String username){
        toGo.add(username);
    }
    public void addToBuy(String username){
        toBuy.add(username);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(realName);
        dest.writeString(name);
        dest.writeString(url);
        dest.writeString(detail);
        dest.writeString(date);
        dest.writeString(time);
        dest.writeString(location);
        dest.writeString(type);
        dest.writeString(fulldate);
        dest.writeString(linkShare);
        dest.writeStringList(toBuy);
        dest.writeStringList(toGo);

    }

}
