import sys
import os


def eprint(*args, **kwargs):
    print(*args, file=sys.stderr, **kwargs)


def create_dir_when_not_exist(dir):
    if not os.path.exists(dir):
        os.makedirs(dir)
