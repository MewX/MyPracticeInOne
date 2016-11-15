object Sieve {

    fun primesUpTo(n: Int) : List<Int> = (2..n)
            .toList()
            .let {
                var temp = it
                for (i in 2..n) temp = temp.filter { it % i != 0 }
                temp
            }

//    fun primesUpTo(n: Int) : List<Int> {
//        val sieve = mutableListOf<Int>()
//        (0..n).forEach { sieve.add(0) }
//        for (i in 2 .. n) {
//            var j = i
//            while (j <= n) {
//                sieve[j] ++
//                j += i
//            }
//        }
//        val result = mutableListOf<Int>()
//        (2 .. n).forEach { if (sieve[it] == 1) result.add(it) }
//        return result
//    }
}