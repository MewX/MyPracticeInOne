package org.mewx.ace.xposedtutorial;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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

            /*
            App Launcher Activity: com.tencent.mm.plugin.appbrand.ui.AppBrandLauncherUI
            App Activity: com.tencent.mm.plugin.appbrand.ui.AppBrandUI
             */
            XposedHelpers.findAndHookMethod("com.tencent.mm.plugin.appbrand.ui.AppBrandUI", lpparam.classLoader, "onCreate", Bundle.class, new XC_MethodHook() {

                public void recursiveLoopChildren(ViewGroup parent) {
                    for (int i = parent.getChildCount() - 1; i >= 0; i--) {
                        final View child = parent.getChildAt(i);
                        if (child instanceof WebView) {
//                             WebView.setWebContentsDebuggingEnabled(true); // API: 19
                            XposedBridge.log("Found Webview!!! " + parent.getContext().getPackageName());
                            recursiveLoopChildren((ViewGroup) child);

                        } else if (child instanceof ViewGroup) {
                            XposedBridge.log("Found a ViewGroup - " + child.getClass().getName());
                            recursiveLoopChildren((ViewGroup) child);
                        } else if (child instanceof TextView) {
                            XposedBridge.log("TextView dump: " + ((TextView) child).getText());
                        }
                    }
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
//                    WebView.setWebContentsDebuggingEnabled(true); // API: 19
                    Activity activity = (Activity) param.thisObject;
                    XposedBridge.log("Enabled web content debugging. Current class name: " + activity.getLocalClassName());
                    recursiveLoopChildren((ViewGroup) activity.getWindow().getDecorView());
                }

            });

            XposedBridge.hookAllConstructors(WebView.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    XposedHelpers.callStaticMethod(WebView.class, "setWebContentsDebuggingEnabled", true);
                }
            });

            XposedBridge.hookAllMethods(WebView.class, "setWebContentsDebuggingEnabled", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    param.args[0] = true;
                }
            });

            // postVisualStateCallback
            // loadUrl
            //XposedHelpers.findAndHookMethod(WebView.class, "loadUrl", String.class, new XC_MethodHook(){
//            XposedHelpers.findAndHookMethod(WebView.class, "postVisualStateCallback", Long.class, WebView.VisualStateCallback.class, new XC_MethodHook(){
//            XposedHelpers.findAndHookMethod(WebViewClient.class, "onPageFinished", WebView.class, String.class, new XC_MethodHook(){
//
//                void recursiveLoopChildren(ViewGroup parent) {
//                    for (int i = parent.getChildCount() - 1; i >= 0; i--) {
//                        final View child = parent.getChildAt(i);
//                        if (child instanceof WebView) {
//                            XposedBridge.log("From WebView Found Webview!!! " + parent.getContext().getPackageName());
//                            recursiveLoopChildren((ViewGroup) child);
//
//                        } else if (child instanceof ViewGroup) {
//                            XposedBridge.log("From WebView Found a ViewGroup - " + child.getClass().getName());
//                            recursiveLoopChildren((ViewGroup) child);
//                        } else if (child instanceof TextView) {
//                            XposedBridge.log("From WebView TextView dump: " + ((TextView) child).getText());
//                        }
//                    }
//                }
//
//                @Override
//                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                    XposedBridge.log("From WebView, after loading, these codes are executed.");
//                    super.afterHookedMethod(param);
//                    recursiveLoopChildren((ViewGroup) param.args[0]);
//                }
//            });

            // not working for text view inside webview
            XposedHelpers.findAndHookMethod(TextView.class, "setText", CharSequence.class, new XC_MethodHook(){
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    CharSequence temp = ((TextView) param.thisObject).getText();
                    if (temp == null || temp.length() == 0) return;
                    XposedBridge.log("From setText in TextView dump: " + temp);
                }
            });
        }
    }
}
