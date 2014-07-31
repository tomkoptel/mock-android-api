package mottimotti.com.sandbox_v8_android.network.request;

import android.content.Context;

import com.google.inject.Inject;
import com.octo.android.robospice.request.SpiceRequest;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.rest.RestService;

import mottimotti.com.sandbox_v8_android.network.RestClientWrapper;
import mottimotti.com.sandbox_v8_android.network.response.FacebookPage;
import roboguice.RoboGuice;
import roboguice.inject.RoboInjector;

@EBean
public class FacebookPageRequest extends SpiceRequest<FacebookPage> {
    @RootContext
    Context context;
    @RestService
    FacebookPageRestClient restClient;
    @Inject
    RestClientWrapper<FacebookPage> wrapper;

    @AfterInject
    void injectRoboGuiceDependencies() {
        // This makes test to work :)
        RoboInjector injector_ = RoboGuice.getInjector(context);
        wrapper = injector_.getInstance(RestClientWrapper.class);


        // http://musingsofaprogrammingaddict.blogspot.com/2009/01/guice-tutorial-part-2-method.html
        // Note the use of Injector.injectMembers() which allows for injection of dependencies into objects,
        // which are not instantiated under the reign of Guice, as it is the case with JUnit test classes.
        // With this setup test is not working.
//        RoboGuice.injectMembers(context, this);



//        Log.d("TEST777", "In test application: " + context.getApplicationContext());
//        Log.d("TEST777", "In test BaseApplicationInjector: " + RoboGuice.getBaseApplicationInjector((Application) context.getApplicationContext()).hashCode());
//        Log.d("TEST777", "In app injector: " + RoboGuice.getInjector(context));
    }

    public FacebookPageRequest() {
        super(FacebookPage.class);
    }

    public void setPageName(CharSequence pageName) {
        wrapper.wrapNetworkCall(() -> restClient.getPage(pageName));
    }

    @Override
    public FacebookPage loadDataFromNetwork() throws Exception {
        return wrapper.loadDataFromNetwork();
    }
}