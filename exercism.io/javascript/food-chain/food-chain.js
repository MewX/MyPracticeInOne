/**
 * Created by mewx on 26/12/16.
 */

var FoodChain = function() {
    var lyrList = [
        // 0
        "I know an old lady who swallowed a fly.\n",
        "I don't know why she swallowed the fly. Perhaps she'll die.\n",

        // 2
        "I know an old lady who swallowed a spider.\n",
        "It wriggled and jiggled and tickled inside her.\n",
        "She swallowed the spider to catch the fly.\n",
        "I don't know why she swallowed the fly. Perhaps she'll die.\n",

        // 6
        "I know an old lady who swallowed a bird.\n",
        "How absurd to swallow a bird!\n",
        "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n",
        "She swallowed the spider to catch the fly.\n",
        "I don't know why she swallowed the fly. Perhaps she'll die.\n",

        // 11
        "I know an old lady who swallowed a cat.\n",
        "Imagine that, to swallow a cat!\n",
        "She swallowed the cat to catch the bird.\n",
        "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n",
        "She swallowed the spider to catch the fly.\n",
        "I don't know why she swallowed the fly. Perhaps she'll die.\n",

        // 17
        "I know an old lady who swallowed a dog.\n",
        "What a hog, to swallow a dog!\n",
        "She swallowed the dog to catch the cat.\n",
        "She swallowed the cat to catch the bird.\n",
        "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n",
        "She swallowed the spider to catch the fly.\n",
        "I don't know why she swallowed the fly. Perhaps she'll die.\n",

        // 24
        "I know an old lady who swallowed a goat.\n",
        "Just opened her throat and swallowed a goat!\n",
        "She swallowed the goat to catch the dog.\n",
        "She swallowed the dog to catch the cat.\n",
        "She swallowed the cat to catch the bird.\n",
        "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n",
        "She swallowed the spider to catch the fly.\n",
        "I don't know why she swallowed the fly. Perhaps she'll die.\n",

        // 32
        "I know an old lady who swallowed a cow.\n",
        "I don't know how she swallowed a cow!\n",
        "She swallowed the cow to catch the goat.\n",
        "She swallowed the goat to catch the dog.\n",
        "She swallowed the dog to catch the cat.\n",
        "She swallowed the cat to catch the bird.\n",
        "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n",
        "She swallowed the spider to catch the fly.\n",
        "I don't know why she swallowed the fly. Perhaps she'll die.\n",

        // 41
        "I know an old lady who swallowed a horse.\n",
        "She's dead, of course!\n"
    ];

    var beg = [0, 2, 6, 11, 17, 24, 32, 41];
    var end = [2, 6, 11, 17, 24, 32, 41, 43];

    this.verse = function(a) {
        return lyrList.slice(beg[a - 1], end[a - 1]).join('');
    };

    this.verses = function (a, b) {
        var min = a > b ? b : a;
        var max = a > b ? a : b;

        var temp = [];
        for (var i = min; i <= max; i ++)
            temp.push(this.verse(i));
        return temp.join('\n') + '\n';
    }
};

module.exports = FoodChain;
