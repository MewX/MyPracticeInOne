package org.mewx.ace.xposedtutorial;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by MewX on 12/12/2017.
 * The entrance of MewX Xposed Tutorial module.
 */

public class MewXposedTutorial implements IXposedHookLoadPackage {

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        XposedBridge.log("MewX outputs, loaded app: " + lpparam.packageName);
    }
}
