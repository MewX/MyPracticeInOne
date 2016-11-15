object Prime {
    // copied the codes from "Sieve"
    fun nth(n: Int) : Int {
        require(n > 0)
        val max = 105000
        val sieve = mutableListOf<Int>()
        (0..max).forEach { sieve.add(0) }
        for (i in 2 .. max) {
            var j = i
            while (j <= max) {
                sieve[j] ++
                j += i
            }
        }
        val result = mutableListOf<Int>()
        (2 .. max).forEach { if (sieve[it] == 1) result.add(it) }
        return result[n - 1]
    }
}