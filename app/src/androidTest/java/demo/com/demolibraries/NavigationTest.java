package demo.com.demolibraries;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import demo.com.ui_components.UIActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class NavigationTest {
    @Rule
    public IntentsTestRule<MainActivity> mainActivity = new IntentsTestRule<>(MainActivity.class);

    @Test
    public void openUIComponent() {
        mainActivity.getActivity();
        onView(withId(R.id.btn_open_ui_components)).perform(click());
        intended(hasComponent(UIActivity.class.getName()));
    }
}
