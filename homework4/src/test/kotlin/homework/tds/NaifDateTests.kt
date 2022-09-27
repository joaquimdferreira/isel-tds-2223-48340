package homework.tds

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class NaifDateTests {
    @Test
    fun `method next month returns correctly when in the same year`() {
        val date = NaifDate(1,Month.JAN,1970)
        assertEquals(2, date.nextMonth())
    }

    @Test
    fun `method next month returns correctly when skipping to next year`() {
        val date = NaifDate(1,Month.DEC,1970)
        assertEquals(1, date.nextMonth())
    }

    @Test
    fun `comparing two identical dates returns true`() {
        val date = NaifDate(1,Month.JAN,1970)
        val expected = NaifDate(1,Month.JAN,1970)
        assertEquals(expected, date)
    }

    @Test
    fun `addDays returns correctly when in the same month`() {
        val date = NaifDate(1,Month.FEB,1970)
        val expected = NaifDate(11,Month.FEB,1970)
        assertEquals(expected, date.addDays(10))
    }

    @Test
    fun `addDays returns correctly when month and year changes`() {
        val date = NaifDate(28, Month.SEP, 2022)
        val expected = NaifDate(23,Month.JAN,2023)
        assertEquals(expected, date.addDays(117))
    }

    @Test
    fun`sucessfully creates a string  from a date in the desired manner`(){
        assertTrue{ "11-MAY-2000" == NaifDate(11,Month.MAY,2000).toString() }
    }
}