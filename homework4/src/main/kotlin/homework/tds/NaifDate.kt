package homework.tds

enum class Month(val m: Int){
    JAN(1),
    FEB(2),
    MAR(3),
    APR(4),
    MAY(5),
    JUN(6),
    JUL(7),
    AUG(8),
    SEP(9),
    OCT(10),
    NOV(11),
    DEC(12);

    companion object {
        fun nextMonth(month: Month): Month = if(month == DEC)JAN
        else Month.values().find { newMonth ->  newMonth.m == month .m+1}
            ?: throw IllegalArgumentException("No such  month")
    }
}

class NaifDate(
    private val day: Int,
    private val month: Month,
    private val year: Int)
{
    init {
        if(day !in 1..31) throw IllegalArgumentException("Invalid day")
        if(month.m !in 1..12) throw IllegalArgumentException("Invalid month")
        if(year < 0) throw IllegalArgumentException("Invalid year")
    }


    fun nextMonth(m: Int = month.m) = if (m == 12) 1 else m + 1

    fun addDays(daysToAdd:Int) :NaifDate{
        var days = daysToAdd
        var d = day
        var m = month
        var y = year
        while(days > 0){
            if(days + d <= daysInMonth(m.m, y)){
                d += days
                break
            }else{
                val daysLeft = daysInMonth(m.m, y) - d
                m = Month.nextMonth(m)
                if(m.m == 1) y++
                days -= (daysLeft + 1)
                d = 1
            }
        }
        return NaifDate(d, m, y)
    }


    private fun daysInMonth(m: Int, y: Int): Int {
        return when (m) {
            1, 3, 5, 7, 8, 10, 12 -> 31
            4, 6, 9, 11 -> 30
            else -> if (y % 4 == 0) 29 else 28
        }
    }
    override fun equals(other: Any?): Boolean {
        if(other !is NaifDate)return false
        return (this.day == other.day) &&
                (this.month == other.month) &&
                (this.year == other.year)
    }

    override fun toString(): String {
        return "$day-$month-$year"
    }
}
