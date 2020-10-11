const fs = require('fs');
const buffer = fs.readFileSync("hello.wasm");

require("./wasm_exec.js");
const go = new Go(); // Defined in wasm_exec.js

WebAssembly.instantiate(new Uint8Array(buffer), go.importObject)
    .then(obj => {
        const wasm = obj.instance;
        go.run(wasm);
    });
