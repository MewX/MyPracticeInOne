# TinyGo practice

This folder uses TinyGo: https://tinygo.org/.

## Install

```shell
$ wget https://github.com/tinygo-org/tinygo/releases/download/v0.15.0/tinygo_0.15.0_amd64.deb
$ sudo dpkg -i tinygo_0.15.0_amd64.deb
```

## Compile to webassembly

```shell
$ tinygo build -o hello.wasm -target=wasm ./hello.go
```

The executor is: https://github.com/tinygo-org/tinygo/blob/release/targets/wasm_exec.js

To run test the wasm file:

```shell
$ node hello.js
```

## Conclusion

Packages are not supported for webassembly.
Therefore, there's a wapm thing: https://wapm.io/ that provides wasm dependency managements.

Also, TinyGo needs a JS executor to run the webassembly,
which is considered not pure webassembly.
