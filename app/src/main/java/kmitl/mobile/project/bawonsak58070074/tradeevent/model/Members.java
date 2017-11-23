package kmitl.mobile.project.bawonsak58070074.tradeevent.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 23/11/2017 AD.
 */

public class Members {
    private List<Member> members = new ArrayList<>();

    public List<Member> getMembers() {
        return members;
    }

    public void addMember(Member member){
        this.members.add(member);
    }
}
