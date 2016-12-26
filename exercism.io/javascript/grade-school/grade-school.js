/**
 * Created by mewx on 26/12/16.
 */

var School = function() {
    var list = { };

    this.add = function (name, grade) {
        if (list.hasOwnProperty(grade))
            list[grade].push(name);
        else
            list[grade] = [name];
        list[grade].sort(function (a, b) { return a.localeCompare(b) })
    };

    this.grade = function (g) {
        return list[g] == undefined ? [ ] : list[g];
    };

    this.roster = function () {
        return list;
    };
};

module.exports = School;
