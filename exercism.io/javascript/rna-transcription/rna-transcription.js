/**
 * Created by mewx on 18/12/16.
 */

var DnaTranscriber = function() {
    this.toRna = function(str) {
        return str.split('').map(function (c) {
            switch (c) {
                case 'G':
                    return 'C';
                case 'C':
                    return 'G';
                case 'T':
                    return 'A';
                case 'A':
                    return 'U';
            }
        }).join();
    }
};

module.exports = DnaTranscriber;
