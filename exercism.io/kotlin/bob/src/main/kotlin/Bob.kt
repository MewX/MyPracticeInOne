object Bob {
    fun hey(str: String) : String = when {
        str.isBlank() -> "Fine. Be that way!"
        str.any(Char::isLetter) && str.filter(Char::isLetter).all(Char::isUpperCase) -> "Whoa, chill out!"
        str.last() == '?' -> "Sure."
        else -> "Whatever."
    }
}