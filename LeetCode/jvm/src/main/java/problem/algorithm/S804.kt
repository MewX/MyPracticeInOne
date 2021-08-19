package problem.algorithm

class S804 {
    fun uniqueMorseRepresentations(words: Array<String>): Int = words
            .map { str -> str
                    .map { arrayOf(".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..")[it - 'a'] }
                    .joinToString(separator = "")
            }
            .toSet().size
}
