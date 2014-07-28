package mottimotti.com.sandbox_v8_android;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.view.Window;
import android.widget.TextView;

import com.google.inject.Inject;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.RoboGuice;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.WindowFeature;

import mottimotti.com.sandbox_v8_android.network.MySpiceService;
import mottimotti.com.sandbox_v8_android.network.request.FacebookPageRequest;
import mottimotti.com.sandbox_v8_android.network.response.FacebookPage;

@EActivity(resName = "activity_my")
@OptionsMenu(R.menu.my)
@WindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS)
@RoboGuice
public class MyActivity extends Activity implements RequestListener<FacebookPage> {
    private SpiceManager spiceManager = new SpiceManager(MySpiceService.class);
    @Inject
    NotifyHelper notifyHelper;
    @ViewById
    TextView preview;
    @Inject
    @Bean
    FacebookPageRequest request;

    @Click
    final void testLambda() {
        setProgressBarIndeterminateVisibility(true);
        notifyHelper.notify("Sending request");
        request.setPageName("Goyello");
        spiceManager.execute(request, this);
    }

    @OptionsItem(R.id.action_settings)
    final void openSettings() {
    }

    @Override
    public void onStart() {
        spiceManager.start(this);
        super.onStart();
    }

    @Override
    public void onStop() {
        spiceManager.shouldStop();
        super.onStop();
    }

    @Override
    public void onRequestFailure(SpiceException spiceException) {
        setProgressBarIndeterminateVisibility(false);
        notifyHelper.notify(spiceException.getMessage());
    }

    @Override
    public void onRequestSuccess(FacebookPage facebookPage) {
        setProgressBarIndeterminateVisibility(false);
        preview.setText(facebookPage.getAbout());
    }
}
