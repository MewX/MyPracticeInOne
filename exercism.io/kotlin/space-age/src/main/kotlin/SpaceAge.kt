class SpaceAge(s: Long) {
    val seconds = s
    val earthYears = s / 31557600.0

    fun onEarth() : Double = convert(earthYears)
    fun onMercury() : Double = convert(earthYears / 0.2408467)
    fun onVenus() : Double = convert(earthYears / 0.61519726)
    fun onMars() : Double = convert(earthYears / 1.8808158)
    fun onJupiter() : Double = convert(earthYears / 11.862615)
    fun onSaturn() : Double = convert(earthYears / 29.447498)
    fun onUranus() : Double = convert(earthYears / 84.016846)
    fun onNeptune() : Double = convert(earthYears / 164.79132)

    fun convert(d: Double): Double = Math.round(d * 100) / 100.0
}
