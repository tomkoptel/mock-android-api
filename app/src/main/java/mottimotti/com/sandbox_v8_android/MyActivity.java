package mottimotti.com.sandbox_v8_android;

import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import com.google.inject.Inject;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.WindowFeature;

import mottimotti.com.sandbox_v8_android.response.FacebookPage;
import mottimotti.com.sandbox_v8_android.retrofit.FacebookService;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import roboguice.activity.RoboActivity;

@EActivity(R.layout.activity_my)
@OptionsMenu(R.menu.my)
@WindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS)
public class MyActivity extends RoboActivity implements Callback<FacebookPage> {
    @Inject
    NotifyHelper notifyHelper;
    @Inject
    RestAdapter restAdapter;
    @ViewById
    TextView preview;

    private FacebookService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mService = restAdapter.create(FacebookService.class);
    }

    @Click
    final void testLambda() {
        setProgressBarIndeterminateVisibility(true);
        notifyHelper.notify("Sending request");
        mService.getPage("Goyello", this);
    }

    @Override
    public void success(FacebookPage facebookPage, Response response) {
        setProgressBarIndeterminateVisibility(false);
        preview.setText(facebookPage.getAbout());
    }

    @Override
    public void failure(RetrofitError error) {
        setProgressBarIndeterminateVisibility(false);
        notifyHelper.notify(error.getMessage());
    }
}
