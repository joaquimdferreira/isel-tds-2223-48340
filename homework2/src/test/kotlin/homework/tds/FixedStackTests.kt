package homework.tds

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class FixedStackTests {
    @Test fun `check new instance of mutable stack is empty`(){
        val stk = makeStack<String>()
        assertTrue(stk.isEmpty())
    }

    @Test fun `check that stack is not empty after push`(){
        var stk = makeStack<String>()
        stk = stk.push("hey")
        assertFalse(stk.isEmpty())
    }

    @Test fun `check that peek returns last pushed item`(){
        var stk = makeStack<String>()
        stk = stk.push("hey")
        assertEquals("hey", stk.peek())
    }

    @Test fun `check that stack is empty after pop last item`(){
        var stk = makeStack<String>()
        stk = stk.push("hey")
        stk = stk.pop()
        assertTrue(stk.isEmpty())
    }

    @Test fun `check that empty stack throws noSuchElementException when tries to pop and is empty`(){
        var stk = makeStack<String>()
        assertFailsWith<NoSuchElementException> { stk = stk.pop() }
    }
}