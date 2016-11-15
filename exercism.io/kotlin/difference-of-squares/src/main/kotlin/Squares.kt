class Squares(val v: Int) {
    fun squareOfSum() : Int = ((v * (v + 1)) shr 1).let { it * it }
    fun sumOfSquares() : Int = v * (v + 1) * ((v shl 1) + 1) / 6
    fun difference() : Int = squareOfSum() - sumOfSquares()
}
