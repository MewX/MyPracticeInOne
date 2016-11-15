object RnaTranscription {
    private val map = mapOf(
            'G' to 'C',
            'C' to 'G',
            'T' to 'A',
            'A' to 'U')
    fun ofDna(rna: String): String = rna.map { map[it] }.joinToString("")
}