/*
 * Copyright (c) 2014. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package mottimotti.com.sandbox_v8_android;

import android.app.Application;

import com.google.inject.AbstractModule;

import retrofit.RestAdapter;

/**
 * Created by samsung on 12/21/14.
 */
public class MyModule  extends AbstractModule {
    public MyModule(Application application) {
    }

    @Override
    protected void configure() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://graph.facebook.com")
                .build();
        bind(RestAdapter.class).toInstance(restAdapter);
    }
}