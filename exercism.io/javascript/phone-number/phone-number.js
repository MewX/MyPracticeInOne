/**
 * Created by mewx on 19/12/16.
 */

var PhoneNumber = function (str) {
    this.phoneNumber = str.match(/\d/g).join("");
    if (this.phoneNumber.length < 10)
        this.phoneNumber = "0000000000";
    else if (this.phoneNumber.length == 11)
        this.phoneNumber = this.phoneNumber[0] == '1' ? this.phoneNumber.substring(1) : "0000000000";

    this.number = function () {
        return this.phoneNumber;
    };

    this.areaCode = function () {
        return this.phoneNumber.substring(0, 3);
    };

    this.toString = function () {
        return "(" + this.phoneNumber.substring(0, 3) + ") " +
            this.phoneNumber.substring(3, 6) + "-" + this.phoneNumber.substring(6);
    };
};

module.exports = PhoneNumber;
