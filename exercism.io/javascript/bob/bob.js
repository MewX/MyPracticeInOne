//
// This is only a SKELETON file for the "Bob" exercise. It's been provided as a
// convenience to get you started writing code faster.
//

var Bob = function() {};

function isLetter(c) {
    return c.toLowerCase() != c.toUpperCase();
}

function isUppercase(c) {
    return c == c.toUpperCase();
}

Bob.prototype.hey = function(input) {
    input.replace("\\s+", "");
    var inArray = input.split("");
    if (input == "") return "Fine. Be that way!";
    else if (inArray.every(isLetter)
            && inArray.filter(isLetter).every(isUppercase))
        return "Whoa, chill out!";
    else if (input.charAt(input.length - 1) == '?') return "Sure.";
    else return "Whatever.";
};

module.exports = Bob;
