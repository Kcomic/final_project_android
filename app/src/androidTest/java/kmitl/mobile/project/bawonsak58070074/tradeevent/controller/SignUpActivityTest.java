package kmitl.mobile.project.bawonsak58070074.tradeevent.controller;

import android.os.SystemClock;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import kmitl.mobile.project.bawonsak58070074.tradeevent.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Kcomic on 26/11/2560.
 */

public class SignUpActivityTest {

    @Rule
    public ActivityTestRule<SignUpActivity> mActivityTestRule = new ActivityTestRule<>(SignUpActivity.class);

    @Test
    public void SignUpTest(){
        onView(withId(R.id.new_fullname)).perform(replaceText("Test5 Test"), closeSoftKeyboard());
        onView(withId(R.id.new_nickname)).perform(replaceText("Test5"), closeSoftKeyboard());
        onView(withId(R.id.new_username)).perform(replaceText("test5"), closeSoftKeyboard());
        onView(withId(R.id.new_password)).perform(replaceText("test5"), closeSoftKeyboard());
        onView(withId(R.id.new_email)).perform(replaceText("test5@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.new_phone)).perform(replaceText("0851245112"), closeSoftKeyboard());
        onView(withId(R.id.signUpBtn)).perform(click());
        SystemClock.sleep(3000);

    }
}
