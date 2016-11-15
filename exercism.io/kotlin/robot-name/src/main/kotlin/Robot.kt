import java.security.SecureRandom

class Robot {
    val random = SecureRandom()
    var name = generateName()

    fun reset() {
        name = generateName()
    }

    private fun generateName() : String = listOf('A' + random.nextInt(26), 'A' + random.nextInt(26), '0' + random.nextInt(10), '0' + random.nextInt(10), '0' + random.nextInt(10))
            .joinToString("")
}