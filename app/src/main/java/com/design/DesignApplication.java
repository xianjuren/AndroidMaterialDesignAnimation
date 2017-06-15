package com.design;

import android.app.ActivityManager;
import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

/**
 * Created by Jone on 17/6/13.
 */

public class DesignApplication extends Application {

    private static final String TAG = "DesignApplication";

    @Override
    public void onCreate() {
        super.onCreate();

        int pid =android.os.Process.myPid();
        ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess:manager.getRunningAppProcesses())
        if(pid==appProcess.pid){
            Log.d(TAG,""+appProcess.processName);
        }

    }
}
