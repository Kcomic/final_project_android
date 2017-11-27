package kmitl.mobile.project.bawonsak58070074.tradeevent.controller;


import android.os.SystemClock;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kmitl.mobile.project.bawonsak58070074.tradeevent.R;
import kmitl.mobile.project.bawonsak58070074.tradeevent.model.Member;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
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
    LoginActivityTest loginActivityTest = new LoginActivityTest();

    @Test
    public void wantToBuy() {

        loginActivityTest.loginActivityTest();
        onView(withId(R.id.eventList)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.wantBuy)).perform(click());
        onView(withText("Yes")).perform(click());
        onView(withText("Check in Complete!")).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    @Test
    public void wantToGo() {

        loginActivityTest.loginActivityTest();
        onView(withId(R.id.eventList)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.wantGo)).perform(click());
        onView(withText("Yes")).perform(click());
        onView(withText("Check in Complete!")).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
        SystemClock.sleep(3000);

    }

    @Test
    public void whoGo() {
        loginActivityTest.loginActivityTest();
        onView(withId(R.id.eventList)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.whoGo)).perform(scrollTo()).perform(click());
        SystemClock.sleep(3000);
    }

    @Test
    public void whoBuy() {
        loginActivityTest.loginActivityTest();
        onView(withId(R.id.eventList)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.whoBuy)).perform(scrollTo()).perform(click());
        SystemClock.sleep(3000);
    }

}
