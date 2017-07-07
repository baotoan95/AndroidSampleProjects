package com.confidence.btit95.confidencesecretbeta.security;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.confidence.btit95.confidencesecretbeta.ConSecretPinActivity;
import com.confidence.btit95.confidencesecretbeta.R;
import com.github.orangegangsters.lollipin.lib.managers.LockManager;

/**
 * Created by baotoan on 04/07/2017.
 */

public class AppSecurity extends Application {
    public static boolean SECURITY_ENABLED = false;
    private final static String LOCK = "LOCK";
    private static SharedPreferences sharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();

        AppSecurity.sharedPreferences = getSharedPreferences("ConfidenceSecret", Context.MODE_PRIVATE);

        if(isEnabledSecurity()) {
            LockManager<ConSecretPinActivity> lockManager = LockManager.getInstance();
            lockManager.enableAppLock(this, ConSecretPinActivity.class);
            lockManager.getAppLock().setLogoId(R.drawable.security_lock);
        }
    }

    private boolean isEnabledSecurity() {
        return AppSecurity.SECURITY_ENABLED = AppSecurity.sharedPreferences.getBoolean(LOCK, false);
    }

    public static void setEnableLock(boolean enable) {
        AppSecurity.SECURITY_ENABLED = enable;
        SharedPreferences.Editor editor = AppSecurity.sharedPreferences.edit();
        editor.putBoolean(AppSecurity.LOCK, AppSecurity.SECURITY_ENABLED);
        editor.commit();
    }
}
