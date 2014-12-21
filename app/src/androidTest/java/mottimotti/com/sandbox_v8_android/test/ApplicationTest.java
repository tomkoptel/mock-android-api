package mottimotti.com.sandbox_v8_android.test;

import android.app.Application;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;

import com.google.inject.AbstractModule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import co.freeside.betamax.Betamax;
import co.freeside.betamax.Recorder;
import mottimotti.com.sandbox_v8_android.MyActivity_;
import mottimotti.com.sandbox_v8_android.R;
import retrofit.RestAdapter;
import roboguice.RoboGuice;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(AndroidJUnit4.class)
public class ApplicationTest extends ActivityInstrumentationTestCase2<MyActivity_> {

    private Application mApplication;

    public ApplicationTest() {
        super(MyActivity_.class);
    }
    @Rule
    public Recorder recorder = new Recorder();

    @Before
    public void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());

        mApplication = (Application) this.getInstrumentation()
                .getTargetContext().getApplicationContext();
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
        RoboGuice.Util.reset();
    }

    @Test
    public void checkPreconditions() {
        assertThat(getActivity(), notNullValue());
        // Check that Instrumentation was correctly injected in setUp()
        assertThat(getInstrumentation(), notNullValue());
    }

    @Betamax(tape="my tape")
    @Test
    public void testInjectionOverride() throws IOException {
        // Create a MockWebServer. These are lean enough that you can create a new
        // instance for every unit test.
//        MockWebServer server = new MockWebServer();
//
//        String mockJson = TestUtils.getJson("facebook_page");
//        server.enqueue(new MockResponse().setBody(mockJson));
//        server.play();
//
//        RoboGuice.overrideApplicationInjector(mApplication, new MyTestModule(server.getPort()));

        getActivity();
        onView(withId(R.id.testLambda)).perform(click());
        onView(withId(R.id.preview)).check(matches(withText("about")));
    }

    private static class MyTestModule extends AbstractModule {
        private final int mPort;

        public MyTestModule(int port) {
            mPort = port;
        }

        @Override
        protected void configure() {
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint("http://localhost:" + mPort)
                    .build();
            bind(RestAdapter.class).toInstance(restAdapter);
        }
    }
}