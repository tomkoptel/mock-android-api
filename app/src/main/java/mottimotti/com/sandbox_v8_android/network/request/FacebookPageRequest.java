package mottimotti.com.sandbox_v8_android.network.request;

import android.app.Activity;
import android.content.Context;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.octo.android.robospice.request.SpiceRequest;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.rest.RestService;

import mottimotti.com.sandbox_v8_android.network.RestClientWrapper;
import mottimotti.com.sandbox_v8_android.network.response.FacebookPage;
import roboguice.RoboGuice;

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
        RoboGuice.injectMembers(context, this);
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