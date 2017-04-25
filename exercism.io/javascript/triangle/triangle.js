/**
 * Created by mewx on 29/12/16.
 */

function Triangle(a, b, c) {
    this.kind = function() {
        var list = [a, b, c];
        list.sort(function(a, b) { return a - b; });

        if (list[0] + list[1] <= list[2]) throw new EventException();
        return list[0] == list[1] || list[1] == list[2] ? (list[0] == list[2]
            ? "equilateral" : "isosceles") : "scalene";
    };
}

module.exports = Triangle;
