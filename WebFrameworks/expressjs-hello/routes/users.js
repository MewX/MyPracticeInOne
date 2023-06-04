var express = require('express');
var router = express.Router();

/* GET users listing. */
router.get('/', function (req, res, next) {
    res.render('error', {error: new Error('WAI Error'), message: 'This page has not been implemented.'})

});

module.exports = router;
