/**
 * Created by mewx on 19/12/16.
 */

var Words = function() {
    this.count = function(words) {
        words = words.trim().toLowerCase();
        var w = words.split(/\s+/g);
        console.log(w);

        var result = {};
        w.forEach(function(word) {
            if (result.hasOwnProperty(word))
                result[word] += 1;
            else
                result[word] = 1;
        });
        return result;
    }
};

module.exports = Words;
