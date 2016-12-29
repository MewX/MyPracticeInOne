/**
 * Created by mewx on 29/12/16.
 */

function at(hour, minute) {
    if (hour == undefined) hour = 0;
    if (minute == undefined) minute = 0;

    var result = { };
    result.actualHour = 0;
    result.actualMinute = 0;

    result.toString = function() {
        var str = ("0" + result.actualHour).slice(-2) + ":" + ("0" + result.actualMinute).slice(-2);
        // console.log(str);
        return str;
    };

    result.plus = function(n) {
        n += result.actualHour * 60 + this.actualMinute;

        result.actualHour = parseInt(n / 60);
        result.actualMinute = n % 60;

        // regularise minutes
        if (result.actualMinute < 0) {
            result.actualMinute += 60;
            result.actualHour -= 1;
        }

        // regularise hours
        result.actualHour %= 24;
        result.actualHour += this.actualHour < 0 ? 24 : 0;
        return result;
    };

    result.minus = function(n) {
        return result.plus(-n);
    };

    result.equals = function(n) {
        // console.log(result === n);
        // console.log("H: " + result.actualHour + " vs. " + n.actualHour);
        // console.log("M: " + result.actualMinute + " vs. " + n.actualMinute);
        return result.actualHour == n.actualHour && this.actualMinute == n.actualMinute;
    };

    // calc the actual time
    result.plus(hour * 60 + minute);
    return result;
}

module.exports = {
    at: at
};
