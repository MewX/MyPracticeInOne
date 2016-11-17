fun main(args: Array<String>) {
    var sum = 0;
    for (case in input) {
        val nums: List<String> = case.split("x")
        val a = nums[0].toInt()
        val b = nums[1].toInt()
        val c = nums[2].toInt()
        val result: List<Int> = listOf(a * b, a * c, b * c)
        sum += result.sum().shl(1) + result.min()!!
    }
    println(sum)
}
