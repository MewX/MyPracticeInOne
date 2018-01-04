import app_helper as app
import sys
import re

PACKAGE_NAME = "com.tencent.mm"
MAIN_ACTIVITY_NAME = "com.tencent.mm.ui.LauncherUI"
APP_LIST_ACTIVITY_NAME = "com.tencent.mm.plugin.appbrand.ui.AppBrandLauncherUI"
APP_LAUNCHER_ACTIVITY_NAME = "com.tencent.mm.plugin.appbrand.ui.AppBrandUI"
ADB_PATH = app.get_adb_bin_full_path()

# start activity
app.start_activity(PACKAGE_NAME, MAIN_ACTIVITY_NAME, ADB_PATH)
temp = app.get_top_activity_name(ADB_PATH)  # universal temp variable
if temp != MAIN_ACTIVITY_NAME:
    print("ERROR: top activity is not correct: " + temp)
    sys.exit(-1)

# click "Discover"
current_ui = app.get_current_view_xml_tree(ADB_PATH)
for elem in current_ui.iter():
    if elem.attrib.get("resource-id") is not None and elem.attrib.get("resource-id") == "com.tencent.mm:id/c3f"\
            and elem.attrib.get("text") is not None and elem.attrib.get("text") == "发现":
        bounds = elem.attrib.get("bounds")
        print("Found node: " + elem.attrib.get("text") + " at " + bounds)

        min_x, min_y, max_x, max_y = app.parse_bounds_text(bounds)
        if min_x is None:
            print("ERROR: cannot parse bound!")
            sys.exit(-2)
        app.click_on_screen(min_x, min_y, max_x, max_y)
        break

# click "small app"
current_ui = app.get_current_view_xml_tree(ADB_PATH)
for elem in current_ui.iter():
    if elem.attrib.get("class") is not None and elem.attrib.get("class") == "android.widget.TextView" \
            and elem.attrib.get("text") is not None and elem.attrib.get("text") == "小程序":
        bounds = elem.attrib.get("bounds")
        print("Found node: " + elem.attrib.get("text") + " at " + bounds)

        min_x, min_y, max_x, max_y = app.parse_bounds_text(bounds)
        if min_x is None:
            print("ERROR: cannot parse bound!")
            sys.exit(-3)
        app.click_on_screen(min_x, min_y, max_x, max_y)
        break

# wait for new activity
app.wait_for_activity_on_top(APP_LIST_ACTIVITY_NAME, ADB_PATH)
# click "PTE hkj"
current_ui = app.get_current_view_xml_tree(ADB_PATH)
for elem in current_ui.iter():
    if elem.attrib.get("class") is not None and elem.attrib.get("class") == "android.widget.TextView" \
            and elem.attrib.get("text") is not None and "PTE" in elem.attrib.get("text"):
        bounds = elem.attrib.get("bounds")
        print("Found node: " + elem.attrib.get("text") + " at " + bounds)

        min_x, min_y, max_x, max_y = app.parse_bounds_text(bounds)
        if min_x is None:
            print("ERROR: cannot parse bound!")
            sys.exit(-4)
        app.click_on_screen(min_x, min_y, max_x, max_y)
        break

# wait for new activity
app.wait_for_activity_on_top(APP_LIST_ACTIVITY_NAME, ADB_PATH)
# TODO: wait for element finished loading

# click to login or already logged in
current_ui = app.get_current_view_xml_tree(ADB_PATH)
for elem in current_ui.iter():
    if elem.attrib.get("class") is not None and elem.attrib.get("class") == "android.webkit.WebView":
        bounds = elem.attrib.get("bounds")
        print("Found node: " + elem.attrib.get("text") + " at " + bounds)

        # TODO: finish those options
        # option 1
        # <node index="0" text="点击登录" resource-id="" class="android.widget.TextView" package="com.tencent.mm" content-desc="" checkable="false" checked="false" clickable="true" enabled="true" focusable="false" focused="false" scrollable="false" long-clickable="false" password="false" selected="false" bounds="[352,1540][729,1687]" />
        # option 2
        '''
        <node index="0" text="" resource-id="com.tencent.mm:id/ji" class="android.widget.LinearLayout" package="com.tencent.mm" content-desc="" checkable="false" checked="false" clickable="false" enabled="true" focusable="false" focused="false" scrollable="false" long-clickable="false" password="false" selected="false" bounds="[160,739][920,1100]">
          <node index="0" text="" resource-id="com.tencent.mm:id/c2v" class="android.widget.LinearLayout" package="com.tencent.mm" content-desc="" checkable="false" checked="false" clickable="false" enabled="true" focusable="false" focused="false" scrollable="false" long-clickable="false" password="false" selected="false" bounds="[160,739][920,953]">
            <node index="0" text="" resource-id="" class="android.widget.ScrollView" package="com.tencent.mm" content-desc="" checkable="false" checked="false" clickable="false" enabled="true" focusable="true" focused="false" scrollable="false" long-clickable="false" password="false" selected="false" bounds="[160,796][920,953]">
              <node index="0" text="" resource-id="" class="android.widget.LinearLayout" package="com.tencent.mm" content-desc="" checkable="false" checked="false" clickable="false" enabled="true" focusable="false" focused="false" scrollable="false" long-clickable="false" password="false" selected="false" bounds="[160,796][920,858]">
                <node index="0" text="" resource-id="com.tencent.mm:id/be0" class="android.widget.LinearLayout" package="com.tencent.mm" content-desc="" checkable="false" checked="false" clickable="false" enabled="true" focusable="false" focused="false" scrollable="false" long-clickable="false" password="false" selected="false" bounds="[217,796][863,858]">
                  <node index="0" text="请点击【允许】进行登录" resource-id="com.tencent.mm:id/c31" class="android.widget.TextView" package="com.tencent.mm" content-desc="" checkable="false" checked="false" clickable="false" enabled="true" focusable="false" focused="false" scrollable="false" long-clickable="false" password="false" selected="false" bounds="[217,796][863,848]" />
                </node>
              </node>
            </node>
          </node>
          <node index="1" text="" resource-id="com.tencent.mm:id/c34" class="android.widget.LinearLayout" package="com.tencent.mm" content-desc="" checkable="false" checked="false" clickable="false" enabled="true" focusable="false" focused="false" scrollable="false" long-clickable="false" password="false" selected="false" bounds="[160,953][920,1100]">
            <node index="0" text="" resource-id="com.tencent.mm:id/c35" class="android.widget.LinearLayout" package="com.tencent.mm" content-desc="" checkable="false" checked="false" clickable="false" enabled="true" focusable="false" focused="false" scrollable="false" long-clickable="false" password="false" selected="false" bounds="[208,991][882,1062]">
              <node index="0" text="确定" resource-id="com.tencent.mm:id/akt" class="android.widget.Button" package="com.tencent.mm" content-desc="" checkable="false" checked="false" clickable="true" enabled="true" focusable="true" focused="false" scrollable="false" long-clickable="false" password="false" selected="false" bounds="[730,991][882,1062]" />
            </node>
          </node>
        </node>'''
        # option 3: logged in
        for e in elem.iter():
            print("Inner: " + str(e.attrib.get("class")))
        break
