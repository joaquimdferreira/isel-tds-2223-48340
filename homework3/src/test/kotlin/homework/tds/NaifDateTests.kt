package homework.tds

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class NaifDateTests {
    @Test
    fun `method next month returns correctly when in the same year`() {
        val date = NaifDate(1,1,1970)
        assertEquals(2, date.nextMonth())
    }

    @Test
    fun `method next month returns correctly when skipping to next year`() {
        val date = NaifDate(1,12,1970)
        assertEquals(1, date.nextMonth())
    }

    @Test
    fun `comparing two identical dates returns true`() {
        val date = NaifDate(1,1,1970)
        val expected = NaifDate(1,1,1970)
        assertTrue { date.compareDates(expected) }
    }

    @Test
    fun `addDays returns correctly when in the same month`() {
        val date = NaifDate(1,2,1970)
        val expected = NaifDate(11,2,1970)
        assertTrue { date.addDays(10).compareDates(expected) }
    }

    @Test
    fun `addDays returns correctly when month and year changes`() {
        val date = NaifDate(28, 9, 2022)
        val expected = NaifDate(23,1,2023)
        assertTrue { date.addDays(117).compareDates(expected) }
    }

    @Test
    fun `toString correctly translates into desired format`(){
        val date = NaifDate(1,1,1970)
        assertTrue { date.toString().compareTo("1-1-1970") == 0 }
    }
}