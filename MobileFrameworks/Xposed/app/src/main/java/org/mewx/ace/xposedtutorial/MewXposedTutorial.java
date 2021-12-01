package org.mewx.ace.xposedtutorial;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.google.common.io.BaseEncoding;

import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import wendu.dsbridge.DWebView;

/**
 * Created by MewX on 12/12/2017.
 * The entrance of MewX Xposed Tutorial module.
 */

public class MewXposedTutorial implements IXposedHookLoadPackage {
    private boolean versionOK = false;
    private Context appContext = null;

    private void checkWeChatVersion(String real, String target) {
        if (real.contains(target)) {
            XposedBridge.log(String.format("WeChat Version is ok: %s, and expecting %s.", real, target));
            versionOK = true;
        } else {
            XposedBridge.log(String.format("WeChat Version is not ok: %s, but expecting %s.", real, target));
            versionOK = false;
        }
    }

    class MyJavaScriptInterface {
        MyJavaScriptInterface() {
        }

        @JavascriptInterface
        @SuppressWarnings("unused")
        public void sendValueFromHtml(String html) {
            String filePath = "/data/system/mewx/ace/dump/html/"; // This folder is created in advance
            XposedBridge.log("Dumped html files are stored in: " + filePath);
            LightCache.saveFile(filePath, "" + System.currentTimeMillis() + ".html", html.getBytes(), true);
        }

    }

    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        // switches
        final boolean HIJACK_WECHAT = true;
        final boolean HIJACK_TRANSORKS = false;

        if (HIJACK_TRANSORKS && lpparam.packageName.contains("fobwifi")) {
            // Transocks: hijacking the bks keystore password.
            XposedBridge.log("MewX outputs, loaded app: " + lpparam.packageName);


            XposedHelpers.findAndHookMethod("java.security.KeyStore", lpparam.classLoader, "load", InputStream.class, char[].class, new XC_MethodHook() {

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    if (((char[]) param.args[1]).length > 0) {
                        XposedBridge.log("GOT IT");
                        char[] pass = (char[]) param.args[1];
                        XposedBridge.log("GOT IT: " + new String(pass));

                        Key key = ((KeyStore) param.thisObject).getKey("new", pass);
                        Log.e("MEWX", "-----BEGIN PRIVATE KEY-----");
                        Log.e("MEWX", BaseEncoding.base64().encode(key.getEncoded()));
                        Log.e("MEWX", "-----END PRIVATE KEY-----");
                    }
                }
            });

        }
        else if (lpparam.packageName.equals("com.android.systemui")) {
            // system ui hook, used to detecting the module is actually running
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

        } else if (HIJACK_WECHAT && lpparam.packageName.equals("com.tencent.mm")) {
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
                    appContext = ((Activity) param.thisObject).getApplicationContext();
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

            // hooking `onCreate` cannot get WebView!
            XposedHelpers.findAndHookMethod("com.tencent.mm.plugin.appbrand.ui.AppBrandUI", lpparam.classLoader, "onCreate", Bundle.class, new XC_MethodHook() {

                void recursiveLoopChildren(ViewGroup parent) {
                    for (int i = parent.getChildCount() - 1; i >= 0; i--) {
                        final View child = parent.getChildAt(i);
                        if (child instanceof WebView) {
//                             WebView.setWebContentsDebuggingEnabled(true); // API: 19
                            XposedBridge.log("Found Webview!!! - " + child.getClass().getName());
                            recursiveLoopChildren((ViewGroup) child);
                            XposedHelpers.callStaticMethod(((WebView) child).getClass(), "setWebContentsDebuggingEnabled", true);

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
                    Activity activity = (Activity) param.thisObject;
                    XposedBridge.log("Enabled web content debugging. Current class name: " + activity.getLocalClassName());
                    recursiveLoopChildren((ViewGroup) activity.getWindow().getDecorView());
                }

            });

            XposedHelpers.findAndHookMethod(ViewGroup.class, "addView", View.class, ViewGroup.LayoutParams.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    if (param.args[0] instanceof WebView) {
                        XposedBridge.log("Found just added Webview!!! - " + param.args[0].getClass().getName());
                        XposedHelpers.callStaticMethod(((WebView) param.args[0]).getClass(), "setWebContentsDebuggingEnabled", true);
                        Class C = param.args[0].getClass();
                        while (C != null) {
                            XposedBridge.log("   -> " + C.getName());
                            C = C.getSuperclass();
                        }
                    }
                }
            });

            XposedBridge.hookAllConstructors(WebView.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    XposedHelpers.callStaticMethod(WebView.class, "setWebContentsDebuggingEnabled", true);
                }
            });

            XposedHelpers.findAndHookMethod(WebView.class, "setWebViewClient", WebViewClient.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    XposedHelpers.callStaticMethod(((WebView) param.thisObject).getClass(), "setWebContentsDebuggingEnabled", true);
                    XposedBridge.log("Setting web view client: " + param.args[0].getClass().getName());
                }
            });

            XposedBridge.hookAllMethods(WebView.class, "setWebContentsDebuggingEnabled", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    param.args[0] = true;
                }
            });


            XposedHelpers.findAndHookMethod("com.tencent.smtt.sdk.WebView", lpparam.classLoader, "loadUrl", String.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);

                    ((WebView) param.thisObject).addJavascriptInterface(new MyJavaScriptInterface(), "interface");

                }
            });

            XposedHelpers.findAndHookMethod("com.tencent.smtt.sdk.SystemWebViewClient", lpparam.classLoader, "onPageFinished", WebView.class, String.class, new XC_MethodHook() {


                void recursiveLoopChildren(ViewGroup parent) {
                    for (int i = parent.getChildCount() - 1; i >= 0; i--) {
                        final View child = parent.getChildAt(i);
                        if (child instanceof ViewGroup) {
                            XposedBridge.log("  -> Found a ViewGroup - " + child.getClass().getName());
                            recursiveLoopChildren((ViewGroup) child);
                        } else if (child instanceof TextView) {
                            XposedBridge.log("  -> TextView dump: " + ((TextView) child).getText());
                        } else {
                            XposedBridge.log("  -> Found a subview: " + child.getClass().getName());
                        }
                    }
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    XposedBridge.log("From WebView, after loading!!! Trying to traverse the webview.");

                    // trying to traverse the webview
                    final DWebView webview = (DWebView) param.args[0];
                    new WaitAndFetchViews().execute(webview);

                    webview.getSettings().setJavaScriptEnabled(true);
                    webview.setJavascriptInterface(new MyJavaScriptInterface());
//                    webview.addJavascriptInterface(new MyJavaScriptInterface(), "interface");
//                    webview.loadUrl("javascript:window.HtmlViewer.showHTML" +
//                            "('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>');");

                    // FIXME: this is confirmed to be an issue from: com.tencent.smtt.sdk.WebView$SystemWebView
                    // http://bbs.mb.qq.com/thread-1944783-1-1.html
                    final int timeout = 0;
                    webview.evaluateJavascript("console.log(\"waiting\");setTimeout((function(){console.log(\"done: \" + window.document.body.outerHTML);return window.document.body.outerHTML}), " + timeout + ");",
                            new ValueCallback<String>() {
                                @Override
                                public void onReceiveValue(String html) {
                                    String filePath = "/data/system/mewx/ace/dump/html/"; // This folder is created in advance
                                    XposedBridge.log(html);
                                    XposedBridge.log("MewX: Dumped html files are stored in: " + filePath);
                                    LightCache.saveFile(filePath, "" + System.currentTimeMillis() + ".html", html.getBytes(), true);
                                }
                            });
                }


                class WaitAndFetchViews extends AsyncTask<WebView, Integer, Integer> {
                    @Override
                    protected Integer doInBackground(WebView... webViews) {
                        XposedBridge.log("Sleeping");
                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        recursiveLoopChildren(webViews[0]);

                        return null;
                    }
                }


            });

            // not working for text view inside webview
//            XposedHelpers.findAndHookMethod(TextView.class, "setText", CharSequence.class, new XC_MethodHook() {
//                @Override
//                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                    super.afterHookedMethod(param);
//                    CharSequence temp = ((TextView) param.thisObject).getText();
//                    if (temp == null || temp.length() == 0) return;
//                    XposedBridge.log("From setText in TextView dump: " + temp);
//                }
//            });
        }
    }
}
