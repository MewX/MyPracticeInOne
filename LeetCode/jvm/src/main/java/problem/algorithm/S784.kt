package problem.algorithm

class S784 {
    fun letterCasePermutation(S: String): List<String> {
        val results = mutableListOf("")
        S.forEach {
            if (it.isLetter()) {
                for (i in 0 until results.size) {
                    results.add(results[i].plus(it.toLowerCase()))
                    results[i] = results[i].plus(it.toUpperCase())
                }
            } else {
                for (i in 0 until results.size) {
                    results[i] = results[i].plus(it)
                }
            }
        }
        return results
    }
}