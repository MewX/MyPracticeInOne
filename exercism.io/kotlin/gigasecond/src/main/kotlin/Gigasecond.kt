import java.time.LocalDate
import java.time.LocalDateTime

class Gigasecond(dateIn: LocalDateTime) {
    constructor(dateIn: LocalDate) : this(dateIn.atStartOfDay())
    val date: LocalDateTime = dateIn.plusSeconds(1000000000)
}
