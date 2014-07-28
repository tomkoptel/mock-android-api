package mottimotti.com.sandbox_v8_android.network;

import android.app.Application;

import com.octo.android.robospice.SpiceService;
import com.octo.android.robospice.persistence.CacheManager;
import com.octo.android.robospice.persistence.exception.CacheCreationException;

public class MySpiceService extends SpiceService {
    @Override
    public CacheManager createCacheManager(Application application)
            throws CacheCreationException {
        return new CacheManager();
    }
}
