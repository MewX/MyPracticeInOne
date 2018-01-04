# Note: `ANDROID_HOME` system variable should be set

import os
import random
import re
import xml.etree.ElementTree as et

# constants
import sys

import time

DEBUG = True
TEMP_FOLDER = "tmp"
RANDOM_RANGE_FOR_FILE = 100


def get_adb_bin_full_path():
    if os.environ.get("ANDROID_HOME") is None:
        print("System environment variable - 'ANDROID_HOME' is not set.")
        return None

    android_home = os.environ.get("ANDROID_HOME")
    separator = '/'
    if android_home.count('\\') != 0:
        # use windows separator
        separator = '\\'

    if android_home[len(android_home) - 1] != separator:
        android_home = android_home + separator
    adb_bin_full_path = android_home + "platform-tools" + separator + "adb"
    return adb_bin_full_path


def init_adb_path(adb_path, device_id=None):
    # TODO: remove default `None` for device_id
    if adb_path is None:
        adb_path = get_adb_bin_full_path()

    # TODO: add multi-device support, need to check whether the device id is already there
    return adb_path


def get_device_list(adb_path=None):
    adb_path = init_adb_path(adb_path)

    list_output = os.popen(adb_path + " devices").read()

    device_list = []
    pattern = re.compile(r"(.+?)\tdevice")
    for match in pattern.finditer(list_output):
        device_list.append(match.group(1))
    return device_list


def get_top_activity_name(adb_path=None):
    adb_path = init_adb_path(adb_path)

    # simplify results
    output = os.popen(adb_path + " shell dumpsys window windows | grep -E \"mCurrentFocus\"").read()
    pattern = re.compile(r"mCurrentFocus.+?{.+?/(.+?)}")
    return pattern.search(output).group(1)


def start_activity(package_name, activity_name, adb_path=None, ):
    adb_path = init_adb_path(adb_path)

    os.popen(adb_path + " shell am start -n " + package_name + "/" + activity_name).read()
    return wait_for_activity_on_top(activity_name, adb_path)


def get_current_view_xml_tree(adb_path=None):
    adb_path = init_adb_path(adb_path)

    # ui dump on phone
    output = os.popen(adb_path + " shell uiautomator dump").read()
    pattern = re.compile(r"to:\s*(.+?.xml)")
    res = pattern.search(output)
    if res is None:
        return None
    xml_path = res.group(1)

    # add extra leading slash to avoid git-bash path issue
    xml_path = "/" + xml_path

    # copy back to current computer
    # TODO: change this to python temp file
    file_name = str(int(random.random() * RANDOM_RANGE_FOR_FILE)) + ".xml"
    file_path = "./" + TEMP_FOLDER + "/" + file_name
    os.popen(adb_path + " pull " + xml_path + " " + file_path).read()
    tree = et.parse(file_path)
    return tree


def parse_bounds_text(bounds):
    p = re.compile(r"\[(\d+),(\d+)\]\[(\d+),(\d+)\]")
    m = p.search(bounds)
    if m is None:
        return None, None, None, None
    return int(m.group(1)), int(m.group(2)), int(m.group(3)), int(m.group(4))


def wait_for_activity_on_top(name, adb_path=None):
    adb_path = init_adb_path(adb_path)

    dead_counter = 20
    while get_top_activity_name(adb_path) != name:
        time.sleep(1)
        dead_counter -= 1

        if dead_counter == 0:
            print("ERROR: Dead loop, couldn't get correct top activity (current: "
                  + get_top_activity_name(adb_path) + ")")
            return False
    print("Not activity - \"" + name + "\"is on top")
    return True


def click_on_screen(min_x, min_y, max_x, max_y, adb_path=None):
    adb_path = init_adb_path(adb_path)
    mid_x = (min_x + max_x) // 2
    mid_y = (min_y + max_y) // 2
    os.popen(adb_path + " shell input tap " + str(mid_x) + " " + str(mid_y)).read()
    return


def scroll_on_screen(from_x, from_y, to_x, to_y, adb_path=None):
    adb_path = init_adb_path(adb_path)

    return


# testing codes, remove them in production level
print(repr(get_device_list()))
print(get_top_activity_name())
# print(repr(start_activity("com.tencent.mm", "com.tencent.mm.ui.LauncherUI")))

