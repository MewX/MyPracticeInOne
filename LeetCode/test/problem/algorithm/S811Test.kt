package problem.algorithm

import org.testng.annotations.Test

import org.testng.Assert.*

class S811Test {
    @Test
    fun example1() {
        assertEquals(S811().subdomainVisits(arrayOf("9001 discuss.leetcode.com")).toSet(),
                arrayOf("9001 discuss.leetcode.com", "9001 leetcode.com", "9001 com").toSet())
    }

    @Test
    fun example2() {
        assertEquals(S811().subdomainVisits(arrayOf("900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org")).toSet(),
                arrayOf("901 mail.com", "50 yahoo.com", "900 google.mail.com", "5 wiki.org", "5 org", "1 intel.mail.com", "951 com").toSet())
    }
}