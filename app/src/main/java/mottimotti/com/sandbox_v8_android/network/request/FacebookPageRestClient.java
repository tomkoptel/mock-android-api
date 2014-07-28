package mottimotti.com.sandbox_v8_android.network.request;

import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Rest;

import mottimotti.com.sandbox_v8_android.network.MyGsonConverter;
import mottimotti.com.sandbox_v8_android.network.response.FacebookPage;

@Rest(rootUrl = "https://graph.facebook.com", converters = MyGsonConverter.class)
public interface FacebookPageRestClient {
    @Get("/{name}")
    FacebookPage getPage(CharSequence name);
}
