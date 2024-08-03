# flutter_hello

A new Flutter project.

For Ubuntu/Debian/CrOStini:

```
# https://docs.flutter.dev/get-started/install/linux/web#development-tools
$ sudo apt-get install -y curl git unzip xz-utils zip libglu1-mesa

# For Linux desktop:
# https://docs.flutter.dev/get-started/install/linux/desktop#development-tools
$ sudo apt-get install \
      clang cmake git \
      ninja-build pkg-config \
      libgtk-3-dev liblzma-dev \
      libstdc++-12-dev
```

During build, if you seeing permission errors in `/usr/local`:
```
$ flutter clean

$ flutter run
```
