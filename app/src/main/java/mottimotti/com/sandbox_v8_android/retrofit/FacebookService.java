/*
 * Copyright (c) 2014. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package mottimotti.com.sandbox_v8_android.retrofit;


import mottimotti.com.sandbox_v8_android.response.FacebookPage;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by samsung on 12/21/14.
 */
public interface FacebookService {
    @GET("/{page}")
    void getPage(@Path("page") String page, Callback<FacebookPage> callback);
}
