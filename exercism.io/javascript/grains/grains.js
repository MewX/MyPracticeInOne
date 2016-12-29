/**
 * Created by mewx on 29/12/16.
 */
var BigInt = require('./big-integer');

function Grains() {
    table = initTable();

    function initTable() {
        var result = {1: new BigInt(1)};
        for (var i = 2; i <= 64; i++) {
            result[i] = new BigInt(result[i - 1]).pow(2);
        }
        return result;
    }

    this.square = function(i) {
        return table[i].toString();
    }
}

module.exports = Grains;
