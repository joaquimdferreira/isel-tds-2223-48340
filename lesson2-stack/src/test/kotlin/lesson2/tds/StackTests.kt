package lesson2.tds

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class StackTests {
    @Test fun `check new instance of mutable stack is empty`(){
        val stk = makeStack<String>()
        assertTrue(stk.isEmpty())
    }

    @Test fun `check that stack is not empty after push`(){
        val stk = makeStack<String>()
        stk.push("ole")
        assertFalse(stk.isEmpty())
    }

    @Test fun `check that peek returns last pushed item`(){
        val stk = makeStack<String>()
        stk.push("ole")
        assertEquals("ole", stk.peek())
    }

    @Test fun `check that stack is empty after pop last item`(){
        val stk = makeStack<String>()
        stk.push("ole")
        stk.pop()
        assertTrue(stk.isEmpty())
    }

    @Test fun `check that empty stack throws noSuchElementException when tries to pop and is empty`(){
        val stk = makeStack<String>()
        assertFailsWith<NoSuchElementException> { stk.pop() }
    }
}