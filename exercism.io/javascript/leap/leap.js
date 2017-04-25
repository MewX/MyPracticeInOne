/**
 * Created by mewx on 4/11/16.
 */

var Year = function(year) {
    this.isLeap = function () {
        return year % 4 == 0 && year % 100 || year % 400 == 0;
    };

};

module.exports = Year;
