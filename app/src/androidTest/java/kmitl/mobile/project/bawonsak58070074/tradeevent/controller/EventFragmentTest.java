package kmitl.mobile.project.bawonsak58070074.tradeevent.controller;


import android.os.SystemClock;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import kmitl.mobile.project.bawonsak58070074.tradeevent.R;
import kmitl.mobile.project.bawonsak58070074.tradeevent.controller.Activity.LoginActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class EventFragmentTest {

//    private Map<String, Object> toBuyList;
//
//    public void reset() {
//        List<String> eventName = new ArrayList<>();
//        eventName.add("Bangkok International Photo Fair 2017");
//        eventName.add("Friends and Family Luxury Sale 2017");
//        eventName.add("Home in Style 2017 Pinklao");
//        eventName.add("HomePro Expo 26");
//        eventName.add("Jim Thompson Sale 2017");
//        eventName.add("Startup Thailand 2017 ภาคตะวันออก");
//        eventName.add("Thailand Mobile Expo 2017");
//
//        DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
//        DatabaseReference mEventsRef = mRootRef.child("event");
//        for (String events : eventName) {
//            toBuyList = new HashMap<String, Object>();
//            DatabaseReference event = mEventsRef.child(events);
//            DatabaseReference toBuy = event.child("toBuy");
//            final List<String> key = new ArrayList<>();
//            toBuy.addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    Map<String, Object> toBuyList = (Map<String, Object>) dataSnapshot.getValue();
//                    this.toBuyList = toBuyList;
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//                    System.out.println("error");
//                }
//            });
//            for(String k : toBuyList.keySet()){
//                if(toBuyList.get(k) == "test5") {
//                    toBuy.child(k).removeValue();
//                }
//            }
//
//        }
//    }

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void wantToBuy() {

        LoginActivityTest.login();
        onView(withId(R.id.homeList)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.wantBuy)).perform(click());
        onView(withText("Yes")).perform(click());
        onView(withText("Check in Complete!")).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
        SystemClock.sleep(2000);
        pressBack();
        onView(withId(R.id.profileBtn)).perform(click());
        onView(withId(R.id.logout)).perform(click());
    }

    @Test
    public void wantToGo() {
        LoginActivityTest.login();
        onView(withId(R.id.homeList)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.wantGo)).perform(click());
        onView(withText("Yes")).perform(click());
        onView(withText("Check in Complete!")).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
        SystemClock.sleep(2000);
        pressBack();
        onView(withId(R.id.profileBtn)).perform(click());
        onView(withId(R.id.logout)).perform(click());

    }

    @Test
    public void whoGo() {
        LoginActivityTest.login();
        onView(withId(R.id.homeList)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.whoGo)).perform(scrollTo()).perform(click());
        SystemClock.sleep(2000);
        onView(withId(R.id.memberListDec)).perform(scrollTo(), RecyclerViewActions.actionOnItemAtPosition(0, click()));
        pressBack();
        pressBack();
        onView(withId(R.id.profileBtn)).perform(click());
        onView(withId(R.id.logout)).perform(click());

    }

    @Test
    public void whoBuy() {
        LoginActivityTest.login();
        onView(withId(R.id.homeList)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.whoBuy)).perform(scrollTo()).perform(click());
        SystemClock.sleep(2000);
        onView(withId(R.id.memberListDec)).perform(scrollTo(), RecyclerViewActions.actionOnItemAtPosition(0, click()));
        pressBack();
        pressBack();
        onView(withId(R.id.profileBtn)).perform(click());
        onView(withId(R.id.logout)).perform(click());
    }

}
