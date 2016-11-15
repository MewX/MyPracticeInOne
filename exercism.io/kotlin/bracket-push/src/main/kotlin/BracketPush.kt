import java.util.*

object BracketPush {
    fun isValid(str: String) : Boolean = str.fold(Stack<Char>()) {
        stack, c -> when (c) {
            in setOf('[', '{', '(') -> stack.push(c)
            '}' -> if (stack.size != 0 && stack.peek() == '{') stack.pop()
            ']' -> if (stack.size != 0 && stack.peek() == '[') stack.pop()
            ')' -> if (stack.size != 0 && stack.peek() == '(') stack.pop()
        }
        stack
    }.size == 0
}