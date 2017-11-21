package kmitl.mobile.project.bawonsak58070074.tradeevent;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Kcomic on 21/11/2560.
 */

public class Member implements Parcelable {
    String username;
    String email;
    String rating;
    String phone;
    String fullname;
    String nickname;

    public Member(String username, String email, String rating, String phone, String fullname, String nickname) {
        this.username = username;
        this.email = email;
        this.rating = rating;
        this.phone = phone;
        this.fullname = fullname;
        this.nickname = nickname;
    }
    protected Member(Parcel in) {
        username = in.readString();
        email = in.readString();
        rating = in.readString();
        phone = in.readString();
        fullname = in.readString();
        nickname = in.readString();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
