package mottimotti.com.sandbox_v8_android.network;

import com.google.inject.Inject;

import mottimotti.com.sandbox_v8_android.functional.MySupplier;

public class RestClientWrapper<T> {
    private MySupplier<T> networkTask;

    @Inject
    public RestClientWrapper() {
    }

    public void wrapNetworkCall(MySupplier<T> task) {
        this.networkTask = task;
    }

    public T loadDataFromNetwork() {
        return networkTask.get();
    }
}
