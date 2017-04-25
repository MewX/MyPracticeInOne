/**
 * Created by mewx on 18/12/16.
 */

var Gigasecond = function(d) {
    this.date = function() {
        return new Date(d.getTime() + 1000000000000);
    }
};

module.exports = Gigasecond;
