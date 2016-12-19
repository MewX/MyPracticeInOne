/**
 * Created by mewx on 19/12/16.
 */

var BeerSong = function () {
    this.verse = function (a) {
        return this.cap(this.head(a)) +  " of beer on the wall, " + this.head(a) + " of beer.\n" +
            this.part3(a) + " " + this.head((a + 99) % 100) + " of beer on the wall.\n";
    };

    this.sing = function (a, b = 0) {
        var result = this.verse(a --);
        while (a >= b) {
            result = result.concat("\n" + this.verse(a));
            a -= 1;
        }
        return result;
    };

    this.cap = function (str) {
        return str[0].toUpperCase() + str.substring(1);
    };

    this.head = function (i) {
        switch (i) {
            case 0:
                return "no more bottles";
            case 1:
                return "1 bottle";
            default:
                return i + " bottles";
        }
    };

    this.part3 = function (i) {
        var it;
        if (i == 1) it = "it";
        else it = "one";

        if (i == 0) return "Go to the store and buy some more,";
        else return "Take " + it + " down and pass it around,";
    };
};

module.exports = BeerSong;
