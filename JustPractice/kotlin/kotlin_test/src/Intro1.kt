/**
Problem Statement

You are given a int[] marks containing the grades you have received so far in a class. Each grade is between 0 and 10, inclusive. Assuming that you will receive a 10 on all future assignments, determine the minimum number of future assignments that are needed for you to receive a final grade of 10. You will receive a final grade of 10 if your average grade is 9.5 or higher.

Definition


Class:	AimToTen
Method:	need
Parameters:	int[]
Returns:	int
Method signature:	int need(int[] marks)
(be sure your method is public)


Constraints

-	marks has between 1 and 50 elements, inclusive.
-	Each element of marks is between 0 and 10, inclusive.

Examples

0)

{9, 10, 10, 9}
Returns: 0
Your average is already 9.5, so no future assignments are needed.
1)

{8, 9}
Returns: 4
In this case you need 4 more assignments. With each completed assignment, your average could increase to 9, 9.25, 9.4 and 9.5, respectively.
2)

{0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
Returns: 950
3)

{10, 10, 10, 10}
Returns: 0

 */

class AimToTen {
    fun need(marks: Array<Int>): Int {
        // n >= 0 > 190*size + 2 * sum
        for (idx: Int in marks.indices)
            marks[idx] *= 10;
        val n = 190 * marks.size - 2 * marks.sum();
        return if (n < 0) 0 else if (n % 10 == 0) n / 10 else n / 10 + 1;
    }
}

fun main(args: Array<String>) {
    print(AimToTen().need(arrayOf(8, 8, 8, 8)))
}