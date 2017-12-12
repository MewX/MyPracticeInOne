package org.mewx.ace.xposedtutorial;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by MewX on 12/12/2017.
 * The entrance of MewX Xposed Tutorial module.
 */

public class MewXposedTutorial implements IXposedHookLoadPackage {
    private boolean versionOK = false;
    private Context appContext = null;

    void checkWeChatVersion(String real, String target) {
        if (real.contains(target)) {
            XposedBridge.log(String.format("WeChat Version is ok: %s, and expecting %s.", real, target));
            versionOK = true;
        } else {
            XposedBridge.log(String.format("WeChat Version is not ok: %s, but expecting %s.", real, target));
            versionOK = false;
        }
    }

    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        if (lpparam.packageName.equals("com.android.systemui")) {
            XposedBridge.log("MewX outputs, loaded app: " + lpparam.packageName);
            XposedHelpers.findAndHookMethod("com.android.systemui.statusbar.policy.Clock", lpparam.classLoader, "updateClock", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    TextView tv = (TextView) param.thisObject;
                    String text = tv.getText().toString();
                    tv.setText(text + " :-)");
                    tv.setTextColor(Color.parseColor("#2196F3"));
                }
            });

        } else if (lpparam.packageName.equals("com.tencent.mm")) {
            XposedBridge.log("MewX outputs, loaded app: " + lpparam.packageName);
            final String SUPPORTTED_WECHAT_VERSION = "6.5.23";

            XposedHelpers.findAndHookMethod("com.tencent.mm.ui.LauncherUI", lpparam.classLoader, "onCreate", Bundle.class, new XC_MethodHook() {
                String wechatVersion = "unknown";

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    if (appContext != null) {
                        return;
                    }
                    XposedBridge.log("LauncherUI hooked.");
                    appContext = ((Activity)param.thisObject).getApplicationContext();
                    PackageInfo pInfo = appContext.getPackageManager().getPackageInfo(lpparam.packageName, 0);
                    if (pInfo != null)
                        wechatVersion = pInfo.versionName;
                    XposedBridge.log("WeChat version=" + wechatVersion);
                    checkWeChatVersion(wechatVersion, SUPPORTTED_WECHAT_VERSION);

                    if (!versionOK) return;
                    XposedBridge.log("Everything is ok!");
//                    hookMethods(lpparam);
                }
            });

        }
    }
}
