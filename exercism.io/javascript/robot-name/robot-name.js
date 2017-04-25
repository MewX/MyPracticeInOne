/**
 * Created by mewx on 26/12/16.
 */

function Robot () {
    var NUM = "0123456789";
    var UL = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    function getRandomChar(str) {
        return str.charAt(Math.floor(Math.random() * str.length));
    }

    function generateNewName() {
        return getRandomChar(UL) + getRandomChar(UL)
            + getRandomChar(NUM) + getRandomChar(NUM) + getRandomChar(NUM);
    }

    function resetName() {
        var temp = generateNewName();
        while (Robot.hashTable[temp] != undefined)
            temp = generateNewName();
        Robot.hashTable[temp] = true;
        return temp;
    }

    this.name = resetName();

    this.reset = function() {
        this.name = resetName();
    }
}

// static hash set
Robot.hashTable = { };

module.exports = Robot;
