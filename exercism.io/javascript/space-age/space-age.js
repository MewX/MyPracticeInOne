/**
 * Created by mewx on 29/12/16.
 */

function SpaceAge(age) {
    this.seconds = age;

    function parseToDay(num) {
        return parseFloat((num / 3600 / 24).toFixed(2));
    }

    function toEarthDay() {
        return age / 365.25;
    }

    this.onEarth = function() {
        return parseToDay(toEarthDay());
    };

    this.onMercury = function() {
        return parseToDay(toEarthDay() / 0.2408467);
    };

    this.onVenus = function() {
        return parseToDay(toEarthDay() / 0.61519726);
    };

    this.onMars = function() {
        return parseToDay(toEarthDay() / 1.8808158);
    };

    this.onJupiter = function() {
        return parseToDay(toEarthDay() / 11.862615);
    };

    this.onSaturn = function() {
        return parseToDay(toEarthDay() / 29.447498);
    };

    this.onUranus = function() {
        return parseToDay(toEarthDay() / 84.016846);
    };

    this.onNeptune = function() {
        return parseToDay(toEarthDay() / 164.79132);
    };
}

module.exports = SpaceAge;
