class Allergies(val n: Int) {
    fun isAllergicTo(type: Allergen) : Boolean = (type.score and n) == type.score
    fun getList() : List<Allergen> = Allergen.values().filter { isAllergicTo(it) }
}