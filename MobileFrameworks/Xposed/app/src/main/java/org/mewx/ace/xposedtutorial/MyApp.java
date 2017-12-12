package org.mewx.ace.xposedtutorial;

import android.app.Application;
import android.content.Context;

/**
 * Created by MewX on 12/13/2017.
 * The helper app context.
 */

public class MyApp extends Application {
    private static Context context;

    public void onCreate() {
        super.onCreate();
        MyApp.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return MyApp.context;
    }
}
