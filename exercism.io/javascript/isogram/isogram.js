/**
 * Created by mewx on 19/12/16.
 */

var Isogram = function(str) {
    this.isLetter = function(str) {
        return str.length === 1 && str.match(/[a-z]/i);
    };

    this.isIsogram = function() {
        str = str.toLowerCase();
        if (str.length <= 1) return true;
        for (var i = 1; i < str.length; i ++) {
            // if (!this.isLetter(str[i])) continue;
            if (str[i] == '-' || str[i] == ' ') continue;
            if (str.substring(0, i).includes(str[i]))
                return false;
        }
        return true;
    };
};

module.exports = Isogram;
