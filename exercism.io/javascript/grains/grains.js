/**
 * Created by mewx on 29/12/16.
 */
var BigInt = require('./big-integer');

function Grains() {
    sum = new BigInt(1);
    table = initTable();

    function initTable() {
        var result = {1: new BigInt(1)};
        for (var i = 2; i <= 64; i++) {
            result[i] = new BigInt(result[i - 1]).add(new BigInt(result[i - 1]));
            sum = sum.add(result[i]);
        }
        return result;
    }

    this.square = function(i) {
        return table[i].toString();
    };

    this.total = function() {
        return sum.toString();
    };
}

module.exports = Grains;
