import java.util.GregorianCalendar
class Year(y: Int) {
    val isLeap = GregorianCalendar().isLeapYear(y)
}