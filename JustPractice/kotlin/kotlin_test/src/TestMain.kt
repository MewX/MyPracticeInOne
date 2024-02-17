/**
 * Created by MewX on 7/24/2016.
 */

fun testFun(a: Any): String = a.toString();

fun outputRange(arr: IntProgression): Unit {
    for (a: Int in arr) {
        print("$a, ")
    }
    println()
}

fun outputList(list: List<Any>) {
    for (l in list)
        print("$l, ")
    println()
}

//fun containsEven(collection: Collection<Int>): Boolean = collection.any { in 1..10 }

fun main(args: Array<String>) {
//    println("test")
//    println(testFun("asdfafd"))

//    val list: Array<Int> = Array(0, )

    outputRange(1..10)
    outputRange(1 until 10)
    outputRange(1..10 step 2)
    outputRange(10 downTo 1)

    val list: List<Any> = listOf(1, "123", 3)
    outputList(list)

    val bTest: Boolean? = null;
    if (bTest == true)
        println("true")
    else
        println(bTest)

    val a: Int = 1 // A boxed Int (java.lang.Integer)
    val b: Long = a.toLong() // implicit conversion yields a boxed Long (java.lang.Long)
    print(a.compareTo(b).toString()) // Surprise! This prints "false" as Long's equals() check for other part to be Long as well
}
