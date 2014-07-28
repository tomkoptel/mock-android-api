package mottimotti.com.sandbox_v8_android.network;

import com.google.gson.GsonBuilder;

import org.springframework.http.converter.json.GsonHttpMessageConverter;

public class MyGsonConverter extends GsonHttpMessageConverter {
    public MyGsonConverter() {
        super();
        GsonBuilder builder = new GsonBuilder();
        builder.serializeNulls();
        builder.disableHtmlEscaping();
        setGson(builder.create());
    }
}
