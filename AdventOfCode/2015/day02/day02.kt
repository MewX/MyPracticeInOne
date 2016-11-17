fun main(args: Array<String>) {
    var sum = 0
    var sum2 = 0
    for (case in input) {
        val nums: List<String> = case.split("x")
        val a: Int = nums[0].toInt()
        val b: Int = nums[1].toInt()
        val c: Int = nums[2].toInt()
        val result: List<Int> = listOf(a * b, a * c, b * c).sorted()
        val result2: List<Int> = listOf(a, b, c).sorted()
        sum += result.sum().shl(1) + result[0]
        sum2 += (result2[0] + result2[1]).shl(1) + a * b * c
    }
    println("part1: $sum")
    println("part2: $sum2")
}
