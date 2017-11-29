package kmitl.mobile.project.bawonsak58070074.tradeevent.controller;

import android.os.SystemClock;
import android.support.test.rule.ActivityTestRule;

import com.google.firebase.database.FirebaseDatabase;

import org.junit.Rule;
import org.junit.Test;

import kmitl.mobile.project.bawonsak58070074.tradeevent.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


public class SignUpActivityTest {

    @Rule
    public ActivityTestRule<SignUpActivity> mActivityTestRule = new ActivityTestRule<>(SignUpActivity.class);

    @Test
    public void SignUpTest(){
        onView(withId(R.id.new_fullname)).perform(replaceText("Test7 Test"), closeSoftKeyboard());
        onView(withId(R.id.new_nickname)).perform(replaceText("Test7"), closeSoftKeyboard());
        onView(withId(R.id.new_username)).perform(replaceText("test7"), closeSoftKeyboard());
        onView(withId(R.id.new_password)).perform(replaceText("test7"), closeSoftKeyboard());
        onView(withId(R.id.new_email)).perform(replaceText("test7@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.new_phone)).perform(replaceText("0851245112"), closeSoftKeyboard());
        onView(withId(R.id.signUpBtn)).perform(click());
        SystemClock.sleep(3000);
        FirebaseDatabase.getInstance().getReference().child("member").child("test7").removeValue();
        onView(withId(R.id.profileBtn)).perform(click());
        onView(withId(R.id.logout)).perform(click());


    }

//    @Test
//    public void SignUpFacebookTest(){
//        onView(withId(R.id.login_button)).perform(click());
//        SystemClock.sleep(2000);
//        onView(withId(R.id.new_phone_facebook)).perform(replaceText("0814124488"), closeSoftKeyboard());
//
//    }
}
