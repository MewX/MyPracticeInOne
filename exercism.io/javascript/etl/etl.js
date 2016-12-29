/**
 * Created by mewx on 29/12/16.
 */

function ETL() {
    this.transform = function(old) {
        var result = { };
        Object.keys(old).forEach(function(property) {
            old[property].forEach(function(v) {
                result[v.toLowerCase()] = parseInt(property);
            })
        });
        return result;
    };
}

module.exports = ETL;
