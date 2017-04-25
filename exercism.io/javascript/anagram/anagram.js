/**
 * Created by mewx on 26/12/16.
 */

var Anagram = function (str) {
    function cmp(a, b) {
        return a > b;
    }

    this.matches = function () {
        // port all elements to one array
        var arr = [];
        for (var i = 0; i < arguments.length; i ++) {
            if (arguments[i].constructor === Array)
                arr = arr.concat(arguments[i]);
            else
                arr.push(arguments[i]);
        }
        console.log(arr);

        var result = [];
        var cvStr = str.toLowerCase().split('').sort(cmp).join('');
        console.log(cvStr);
        for (var i = 0; i < arr.length; i ++) {
            var s = arr[i];
            var cvS = s.toLowerCase().split('').sort(cmp).join('');
            console.log(cvS);
            if (cvStr == cvS && str.toLowerCase() != s.toLowerCase()) result.push(s);
        }
        return result;
    };
};

module.exports = Anagram;
