package org.mewx.webviewdumptest;

import android.app.Instrumentation;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiSelector;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("org.mewx.webviewdumptest", appContext.getPackageName());

        Instrumentation instr = InstrumentationRegistry.getInstrumentation();
        UiDevice device = UiDevice.getInstance(instr);
        UiSelector selector = new UiSelector();
        UiObject webView = new UiObject(selector.className(android.webkit.WebView.class.getName()));
        int index = 0;

        assertEquals(webView.getPackageName(), "com.tencent.mm");
        assertNotEquals(webView.getChildCount(), 0);
        Log.e("TEST", "Child count: " + webView.getChildCount());
        System.out.println("TEST: Child count: " + webView.getChildCount());
        while (index < webView.getChildCount()) {
            UiObject listItem = webView.getChild(selector.index(index));
            Log.e("TEST", listItem.getBounds().flattenToString());
            System.out.println("TEST" + listItem.getBounds().flattenToString());
        }

//        webView.clickTopLeft();
//        UiDevice device = UiDevice.getInstance();
//        device.clearLastTraversedText();
//        device.pressDPadDown();
//        String text = device.getLastTraversedText();
//        assertTrue("Read regular text", text.contains("This is test <b>6</b>"));
//        device.pressDPadDown();
//        text = device.getLastTraversedText();
//        assertTrue("Anchor text", text.contains("<a"));
//        device.pressDPadDown();
//        text = device.getLastTraversedText();
//        assertTrue("h5 text", text.contains("h5"));
//        device.pressDPadDown();
//        text = device.getLastTraversedText();
//        assertTrue("Anchor text", text.contains("<a"));
//        device.pressDPadDown();
//        text = device.getLastTraversedText();
//        assertTrue("h4 text", text.contains("h4"));

//        UiObject list = device.findObject(new UiSelector().resourceId(PACKAGE + ":id/" + resourceId));
//
//        int index = 0;
//        int count = 0;
//
//        while (index < list.getChildCount()) {
//            UiObject listItem = list.getChild(selector.index(index));
//
//            Set<String> examinedItems = new LinkedHashSet<>();
//
//            //if first item is a bit out of view (under toolbar) then skip it
//            if (listItem.getBounds().top < TOOLBAR_BOTTOM_Y) {
//                index++;
//                continue;
//                //this will only ever happen once = reached end of list
//            }
//
//            //get unique details from each item
//            //for example, you might get a text field for that list item list this
//            //UiObject textField = listItem.getChild(new UiSelector().resourceId(PACKAGE + ":id/" + childResourceId));
//            String itemDetails = ...;
//
//            //this would be relevent if the list was perfectly scrolled to the top and we don't know we are at the end of the list
//            if (examinedItems.contains(itemDetails)) {
//                index++;
//                continue;
//            }
//
//            //do any actual testing on the item here..
//            count++;
//
//            //if index > 0 we have reached the end of the list
//            if (index == 0) {
//                //you'll need to inherit from InstrumentationTestCase so you can pass an instance to this method
//                TouchUtils.drag(this, CENTER_X, CENTER_X, START_SCROLL_Y, START_SCROLL_Y - ITEM_HEIGHT, 150);
//            }
//
//            examinedItems.add(itemDetails);
    }
}
