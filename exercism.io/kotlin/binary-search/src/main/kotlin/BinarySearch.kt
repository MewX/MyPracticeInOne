object BinarySearch {
    fun search(sortedList: List<Int>, key: Int) : Int {
        require(isSorted(sortedList))
        var a = 0
        var b = sortedList.size - 1
        while (a <= b) {
            val mid = (a + b) shr 1
            if (key == sortedList[mid]) return mid
            else if (key < sortedList[mid]) b = mid - 1
            else a = mid + 1
        }
        return -1
    }

    fun isSorted(list: List<Int>) : Boolean = (0 .. list.size - 2)
            .map { list[it + 1] - list[it] }
            .all { it >= 0 }
}