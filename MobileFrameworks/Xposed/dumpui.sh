adb shell uiautomator dump
adb pull "//storage/emulated/legacy/window_dump.xml" "dump/$1.xml"
