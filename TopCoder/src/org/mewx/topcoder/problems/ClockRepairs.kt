package org.mewx.topcoder.problems

class ClockRepairs {
    fun days(arrivals: Array<Int>, numPerDay: Int) : Int {
        var count = 0;
        return (arrivals.fold<Int, Int>(0) {
            acc, t ->
                var temp = t + acc
                if (temp == 0) 0
                else {
                    count ++;
                    if (temp > numPerDay) temp - numPerDay else 0
                }
        } + numPerDay - 1) / numPerDay + count 
    }
}

fun main(args: Array<String>) {
    println(ClockRepairs().days(arrayOf(10, 0, 0, 4, 20), 8))
    println(ClockRepairs().days(arrayOf(0, 0, 0), 10))
    println(ClockRepairs().days(arrayOf(100, 100), 10))
    println(ClockRepairs().days(arrayOf(27, 0, 0, 0, 9), 9))
    println(ClockRepairs().days(arrayOf(6, 5, 4, 3, 2, 1, 0, 0, 1, 2, 3, 4, 5, 6), 3))
}
