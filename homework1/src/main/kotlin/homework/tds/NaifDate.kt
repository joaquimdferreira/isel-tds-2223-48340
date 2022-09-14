package homework.tds

class NaifDate(val day: Int,val month: Int,val year: Int) {
    private val normalYear = mutableListOf<Int>(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
    private val specialYear = mutableListOf<Int>(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)

    fun nextDay() = if(day == findMonth()) 1 else day + 1
    fun nextMonth() = if(month == 12)1 else month + 1
    fun nextYear() = year + 1

    fun addDays(days: Int): NaifDate {
        var localDay = day
        var localMonth = month
        var localYear = year
        for(i in 0 until days){
            //skipping month
            if(localDay == findMonthSpecificDate(localMonth, localYear)) {
                localDay = 1
                //skipping year
                if(localMonth == 12) {
                    localMonth = 1
                    localYear++
                }
                else localMonth++
            }
            else localDay++
        }
        return NaifDate(localDay, localMonth, localYear)
    }

    private fun findMonth() = if(year % 4 == 0)specialYear[month-1] else normalYear[month-1]

    private fun findMonthSpecificDate(sMonth: Int, sYear: Int) = if(sYear % 4 == 0)specialYear[sMonth-1] else normalYear[sMonth-1]

    override fun toString(): String {
        return "$day-$month-$year"
    }
}