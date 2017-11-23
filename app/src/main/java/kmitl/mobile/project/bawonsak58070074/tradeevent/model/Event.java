package kmitl.mobile.project.bawonsak58070074.tradeevent.model;

import java.util.List;

/**
 * Created by Kcomic on 21/11/2560.
 */

public class Event {
    private String name;
    private String url;
    private String detail;
    private String date;
    private String time;
    private String location;
    private String type;
    private String fulldate;
    private String linkShare;
    private List toBuy, toGo;

    public Event(String name, String url, String detail, String date, String time, String location, String type, String fulldate, String linkShare) {
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

    public List getToBuy() {
        return toBuy;
    }

    public void setToBuy(List toBuy) {
        this.toBuy = toBuy;
    }

    public List getToGo() {
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
}
