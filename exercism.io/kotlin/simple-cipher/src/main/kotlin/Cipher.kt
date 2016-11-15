class Cipher(val inKey: String = "") {
    constructor() : this("blablabla")

    private val random = java.security.SecureRandom()
    val key: String
    private fun randomKey() : String = (1..100).map { (random.nextInt(26) + 'a'.toInt()).toChar() }.joinToString("")
    init {
        require(!inKey.isEmpty() && inKey.all { it.isLetter() && it.isLowerCase() })
        key = if (!inKey.equals("blablabla")) inKey else randomKey()
    }

    fun encode(str: String) : String = str.zip(key)
            .map { ((it.first.toInt() + it.second.toInt() - 'a'.toInt().shl(1)) % 26 + 'a'.toInt()).toChar() }
            .joinToString("")

    fun decode(str: String) : String = str.zip(key)
            .map { ((it.first.toInt() + 26 - 'a'.toInt() - (it.second.toInt() - 'a'.toInt())) % 26 + 'a'.toInt()).toChar() }
            .joinToString("")
}