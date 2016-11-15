class PhoneNumber(num: String) {
    val number = parse(num.filter(Char::isDigit))
    val areaCode = number.substring(0, 3)

    private fun parse(s: String) : String {
        return when {
            s.length == 10 -> s
            s.length == 11 && s[0] == '1' -> s.substring(1)
            else -> "0000000000"
        }
    }

    override fun toString() : String =
            String.format("(%s) %s-%s", areaCode, number.substring(3, 6), number.substring(6))
}