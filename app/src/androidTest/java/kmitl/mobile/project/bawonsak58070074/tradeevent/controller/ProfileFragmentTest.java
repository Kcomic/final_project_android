package kmitl.mobile.project.bawonsak58070074.tradeevent.controller;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import kmitl.mobile.project.bawonsak58070074.tradeevent.R;
import kmitl.mobile.project.bawonsak58070074.tradeevent.controller.Activity.LoginActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * Created by student on 27/11/2017 AD.
 */

public class ProfileFragmentTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);
    LoginActivityTest loginActivityTest = new LoginActivityTest();

    @Test
    public void logoutTest(){
        LoginActivityTest.login();
        onView(withId(R.id.profileBtn)).perform(click());
        onView(withId(R.id.logout)).perform(click());
    }
}
