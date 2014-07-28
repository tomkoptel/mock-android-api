package mottimotti.com.sandbox_v8_android;

import android.content.Context;
import android.widget.Toast;

import com.google.inject.Inject;

import roboguice.inject.ContextSingleton;

@ContextSingleton
public class NotifyHelper {
    @Inject private Context context;

    public void notify(int messageResId) {
        notify(context.getResources().getString(messageResId));
    }

    public void notify(String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
