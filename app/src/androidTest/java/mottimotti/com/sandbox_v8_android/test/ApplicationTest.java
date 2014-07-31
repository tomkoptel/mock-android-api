package mottimotti.com.sandbox_v8_android.test;

import android.app.Application;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

import com.google.gson.Gson;
import com.google.inject.AbstractModule;
import com.google.inject.util.Modules;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import mottimotti.com.sandbox_v8_android.MyActivity_;
import mottimotti.com.sandbox_v8_android.R;
import mottimotti.com.sandbox_v8_android.network.RestClientWrapper;
import mottimotti.com.sandbox_v8_android.network.response.FacebookPage;
import roboguice.RoboGuice;

import static com.google.android.apps.common.testing.ui.espresso.Espresso.onView;
import static com.google.android.apps.common.testing.ui.espresso.action.ViewActions.click;
import static com.google.android.apps.common.testing.ui.espresso.assertion.ViewAssertions.matches;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withId;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withText;
import static org.mockito.Mockito.when;

public class ApplicationTest extends ActivityInstrumentationTestCase2<MyActivity_> {
    @Mock
    RestClientWrapper mockRequest;

    public ApplicationTest() {
        super(MyActivity_.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        MockitoAnnotations.initMocks(this);
        Log.d("TEST777", "=============================================1");

        String mockJson = TestUtils.getJson("facebook_page");
        FacebookPage page = new Gson().fromJson(mockJson, FacebookPage.class);
        when(mockRequest.loadDataFromNetwork()).thenReturn(page);

        Application application = (Application) this.getInstrumentation()
                .getTargetContext().getApplicationContext();
        RoboGuice.setBaseApplicationInjector(application,
                RoboGuice.DEFAULT_STAGE,
                Modules.override(RoboGuice.newDefaultRoboModule(application))
                        .with(new MyTestModule()));
//        Log.d("TEST777", "In test application: " + application);
//        Log.d("TEST777", "In test BaseApplicationInjector: " + RoboGuice.getBaseApplicationInjector(application));
//        Log.d("TEST777", "In test injector: " + RoboGuice.getInjector(application));

        getActivity();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        RoboGuice.util.reset();
    }


    public void testInjectionOverride() {
        onView(withId(R.id.testLambda)).perform(click());
        onView(withId(R.id.preview)).check(matches(withText("about")));
    }

    public class MyTestModule extends AbstractModule {
        @Override
        protected void configure() {
            bind(RestClientWrapper.class).toInstance(mockRequest);
        }
    }
}