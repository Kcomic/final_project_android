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

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * Created by student on 27/11/2017 AD.
 */

@LargeTest
@RunWith(AndroidJUnit4.class)
public class HomeFragmentTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void wantToGo() {
        LoginActivityTest.login();
        onView(withId(R.id.eventBtn)).perform(click());
        SystemClock.sleep(2000);
        onView(withId(R.id.eventList)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.wantGo)).perform(click());
        onView(withText("Yes")).perform(click());
        onView(withText("Check in Complete!")).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
        SystemClock.sleep(2000);
        pressBack();
        onView(withId(R.id.profileBtn)).perform(click());
        onView(withId(R.id.logout)).perform(click());

    }

    @Test
    public void wantToBuy() {
        LoginActivityTest.login();
        onView(withId(R.id.eventBtn)).perform(click());
        SystemClock.sleep(2000);
        onView(withId(R.id.eventList)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.wantBuy)).perform(click());
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
        onView(withId(R.id.eventBtn)).perform(click());
        SystemClock.sleep(2000);
        onView(withId(R.id.eventList)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
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
        onView(withId(R.id.eventBtn)).perform(click());
        SystemClock.sleep(2000);
        onView(withId(R.id.eventList)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.whoBuy)).perform(scrollTo()).perform(click());
        SystemClock.sleep(2000);
        onView(withId(R.id.memberListDec)).perform(scrollTo(), RecyclerViewActions.actionOnItemAtPosition(0, click()));
        pressBack();
        pressBack();
        onView(withId(R.id.profileBtn)).perform(click());
        onView(withId(R.id.logout)).perform(click());
    }

    @Test
    public void goback(){
        LoginActivityTest.login();
        onView(withId(R.id.eventBtn)).perform(click());
        onView(withId(R.id.homeBtn)).perform(click());
    }


}
