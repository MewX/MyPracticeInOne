/**
 * Created by mewx on 19/12/16.
 */

var Pangram = function(str) {
    this.isPangram = function() {
        str = str.toLowerCase();
        var result = true;
        "abcdefghijklmnopqrstuvwxyz".split("").forEach(function (c) {
            if (!str.includes(c)) result = false;
        });
        return result;
    };
};

module.exports = Pangram;
