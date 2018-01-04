# Note: `ANDROID_HOME` system variable should be set

import os
import re


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


def get_device_list(adb_path=None):
    if adb_path is None:
        adb_path = get_adb_bin_full_path()

    list_output = os.popen(adb_path + " devices").read()

    device_list = []
    pattern = re.compile(r"(.+?)\tdevice")
    for match in pattern.finditer(list_output):
        device_list.append(match.group(1))
    return device_list


def get_top_activity_name(adb_path=None):
    # TODO: add multi-device support
    if adb_path is None:
        adb_path = get_adb_bin_full_path()

    # TODO: simplify results
    return os.popen(adb_path + " shell dumpsys window windows | grep -E \"mCurrentFocus|mFocusedApp\"").read()


# testing codes, remove them in production level
print(repr(get_device_list()))
print(get_top_activity_name())
