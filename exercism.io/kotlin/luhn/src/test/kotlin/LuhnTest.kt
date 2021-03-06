import org.junit.Test
import kotlin.test.assertEquals

class LuhnTest {

    @Test
    fun checkDigitIsRightMostDigit() {
        val expectedOutput = 7

        assertEquals(expectedOutput, Luhn(34567).checkDigit)
    }

    @Test
    fun addendsDoublesEveryOtherNumberFromRight() {
        val expectedOutput = listOf(1, 4, 1, 4, 1)

        assertEquals(expectedOutput, Luhn(12121).addends)
    }

    @Test
    fun addendsSubtracts9WhenDoubledNumberIsMoreThan9() {
        val expectedOutput = listOf(7, 6, 6, 1)

        assertEquals(expectedOutput, Luhn(8631).addends)
    }

    @Test
    fun checkSumAddsAddendsTogether1() {
        val expectedOutput = 22

        assertEquals(expectedOutput, Luhn(4913).checksum)
    }

    @Test
    fun checkSumAddsAddendsTogether2() {
        val expectedOutput = 21

        assertEquals(expectedOutput, Luhn(201773).checksum)
    }

    @Test
    fun numberIsValidWhenChecksumMod10IsZero1() {
        val expectedOutput = false

        assertEquals(expectedOutput, Luhn(738).isValid)
    }

    @Test
    fun numberIsValidWhenChecksumMod10IsZero2() {
        val expectedOutput = true

        assertEquals(expectedOutput, Luhn(8739567).isValid)
    }

    @Test
    fun luhnCanCreateSimpleNumbersWithValidCheckDigit() {
        val expectedOutput = 1230L

        assertEquals(expectedOutput, Luhn(123).create)
    }

    @Test
    fun luhnCanCreateLargeNumbersWithValidCheckDigit() {
        val expectedOutput = 8739567L

        assertEquals(expectedOutput, Luhn(873956).create)
    }

    @Test
    fun luhnCanCreateHugeNumbersWithValidCheckDigit() {
        val expectedOutput = 8372637564L

        assertEquals(expectedOutput, Luhn(837263756).create)
    }
}
