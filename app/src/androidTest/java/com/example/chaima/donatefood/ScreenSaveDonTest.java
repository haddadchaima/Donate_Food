package com.example.chaima.donatefood;



import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.chaima.donatefood.Donater.ActivitySaveDon;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.core.AllOf.allOf;


@RunWith(AndroidJUnit4.class)
public class ScreenSaveDonTest {


    @Rule
    public IntentsTestRule<ActivitySaveDon> mIntentsRule =
            new IntentsTestRule<ActivitySaveDon>(ActivitySaveDon.class);


   /* @Test
    public void ClickSaveDonButton () {
        onView(withId(R.id.ediTxtTitle)).perform(typeText("Pizza"), closeSoftKeyboard());
        onView(withId(R.id.ediTxtTitle)).check(matches(withText("Pizza")));

        onView(withId(R.id.spinnerNumberPerson)).perform(click());

        onData(allOf(is(instanceOf(String.class)), is("5 or 6"))).perform(click());

        onView(withId(R.id.editTxtPickUpTime)).perform(typeText("12:00AM"), closeSoftKeyboard());
        onView(withId(R.id.ediTxtSetAdr)).perform(typeText("Tunis"), closeSoftKeyboard());
    }*/


        // Create a bitmap we can use for our simulated camera image
      /*  Bitmap icon = BitmapFactory.decodeResource(
                InstrumentationRegistry.getTargetContext().getResources(),
                R.mipmap.ic_launcher);

        // Build a result to return from the Camera app
        Intent resultData = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        resultData.putExtra("data", icon);
        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData);

        // Stub out the Camera. When an intent is sent to the Camera, this tells Espresso to respond
        // with the ActivityResult we just created
        intending(toPackage("com.example.chaima.donatefood")).respondWith(result);

        // Now that we have the stub in place, click on the button in our app that launches into the Camera

        onView(withId(R.id.button_image)).perform(click());
       // onView(withId(R.id.imageView)).check(matche())

        // We can also validate that an intent resolving to the "camera" activity has been sent out by our app
        intended(toPackage("com.example.chaima.donatefood"));*/

        //onView(withId(R.id.btnSaveDon)).perform(click());

        /*Intent intent = new Intent(mActivityRule.getActivity() , FragmentDonation.class);
        mActivityRule.launchActivity(intent);*/
       // intent.putExtra("Extra", "Donation");


       // onView(withId(R.id.spinnerNumberPerson)).perform(pressMenuKey());
        //onView(withId()).check(matches(withText("Don Food it's saved with success")))

       @Before
       public void stubCameraIntent() {
           Instrumentation.ActivityResult result = createImageCaptureStub();

           // Stub the Intent.
           intending(hasAction(MediaStore.ACTION_IMAGE_CAPTURE)).respondWith(result);
       }

    @Test
    public void testTakePhoto() {
        // Check that the ImageView doesn't have a drawable applied.
        //onView(withId(R.id.imageView)).check(matches(not(hasDrawable())));

        // Click on the button that will trigger the stubbed intent.
        onView(withId(R.id.button_image)).perform(click());

        // With no user interaction, the ImageView will have a drawable.
//        onView(withId(R.id.imageView)).check(matches(hasDrawable()));
    }


    private Instrumentation.ActivityResult createImageCaptureStub() {
        // Put the drawable in a bundle.

        Bundle bundle = new Bundle();
        bundle.putParcelable(ActivitySaveDon.KEYGUARD_SERVICE, BitmapFactory.decodeResource(
                mIntentsRule.getActivity().getResources(),R.mipmap.ic_launcher));

        // Create the Intent that will include the bundle.
        Intent resultData = new Intent();
        resultData.putExtras(bundle);

        // Create the ActivityResult with the Intent.
        return new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData);
    }


}
