package kmitl.mobile.project.bawonsak58070074.tradeevent.model;

import android.net.Uri;
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
    String url;

    public Member(String username, String email, String rating, String phone, String fullname, String nickname, String url) {
        this.username = username;
        this.email = email;
        this.rating = rating;
        this.phone = phone;
        this.fullname = fullname;
        this.nickname = nickname;
        this.url = url;
    }
    protected Member(Parcel in) {
        username = in.readString();
        email = in.readString();
        rating = in.readString();
        phone = in.readString();
        fullname = in.readString();
        nickname = in.readString();
        url = in.readString();
    }

    public static final Creator<Member> CREATOR = new Creator<Member>() {
        @Override
        public Member createFromParcel(Parcel in) {
            return new Member(in);
        }

        @Override
        public Member[] newArray(int size) {
            return new Member[size];
        }
    };

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(email);
        dest.writeString(rating);
        dest.writeString(phone);
        dest.writeString(fullname);
        dest.writeString(nickname);
        dest.writeString(url);

    }
}
