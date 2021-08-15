package problem.algorithm

import org.testng.annotations.Test

import org.testng.Assert.*

class S824Test {
    @Test
    fun example1() {
        assertEquals(S824().toGoatLatin("I speak Goat Latin"),
                "Imaa peaksmaaa oatGmaaaa atinLmaaaaa")
    }

    @Test
    fun example2() {
        assertEquals(S824().toGoatLatin("The quick brown fox jumped over the lazy dog"),
                "heTmaa uickqmaaa rownbmaaaa oxfmaaaaa umpedjmaaaaaa overmaaaaaaa hetmaaaaaaaa azylmaaaaaaaaa ogdmaaaaaaaaaa")
    }
}