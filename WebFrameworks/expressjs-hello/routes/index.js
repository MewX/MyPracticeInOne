var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function (req, res, next) {
    res.render('index', {title: 'Express'});
});

// Get something else.
router.get('/hello', function (req, res, next) {
    res.render('layout', {title: 'Hello Express.js', content: 'Hello contents.'});
});

module.exports = router;
