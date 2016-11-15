// Well, it sounds silly, but that's real - 406ms solution
// I'm gonna using brute force for this DP problem :P
class Change(set: Set<Int>) {
    val set = set.sortedDescending()
    var all = mutableSetOf<List<Int>>()
    var currentShortest = Int.MAX_VALUE
    fun issue(num: Int) : List<Int> {
        require(num > 0 && num >= set.last() || num == 0)
        dfs(num, listOf())
        return if (all.isEmpty()) listOf<Int>() else all.sortedBy { it.size }[0].sorted()
    }

    private fun dfs(num: Int, cur: List<Int>) {
        if (cur.size >= currentShortest) return
        val sum = cur.sum()
        for (coin in set) {
            if (sum + coin > num || coin > cur.min() ?: Int.MAX_VALUE) continue
            else if (sum + coin == num) {
                all.add(cur + coin)
                currentShortest = Math.min(cur.size + 1, currentShortest)
            }
            else if (cur.size < currentShortest) dfs(num, cur + coin)
        }
    }
}