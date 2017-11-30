package kmitl.mobile.project.bawonsak58070074.tradeevent.controller;


import android.os.SystemClock;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.google.firebase.database.FirebaseDatabase;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import kmitl.mobile.project.bawonsak58070074.tradeevent.R;
import kmitl.mobile.project.bawonsak58070074.tradeevent.controller.Activity.LoginActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void loginActivityTest() {
        login();
        onView(withId(R.id.profileBtn)).perform(click());
        onView(withId(R.id.logout)).perform(click());
    }

    @Test
    public void loginFacebookTest() {
        onView(withId(R.id.login_button)).perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.login_button)).perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.new_phone_facebook)).perform(replaceText("0946251425"));
        onView(withId(R.id.loginBtnPhone)).perform(click());
        FirebaseDatabase.getInstance().getReference().child("member").child("1500799393301559").removeValue();
        SystemClock.sleep(2000);
        onView(withId(R.id.profileBtn)).perform(click());
        onView(withId(R.id.logout)).perform(click());
    }

    public static void login(){
        onView(withId(R.id.username)).perform(replaceText("test5"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(replaceText("test5"), closeSoftKeyboard());
        onView(withId(R.id.loginBtn)).perform(click());
        SystemClock.sleep(2000);
    }

    @Test
    public void loginFailTest1(){
        onView(withId(R.id.username)).perform(replaceText(""), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(replaceText(""), closeSoftKeyboard());
        onView(withId(R.id.loginBtn)).perform(click());
        SystemClock.sleep(1000);
    }

    @Test
    public void loginFailTest2(){
        onView(withId(R.id.username)).perform(replaceText("asdasd"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(replaceText("asdasd"), closeSoftKeyboard());
        onView(withId(R.id.loginBtn)).perform(click());
        SystemClock.sleep(1000);
    }


}
