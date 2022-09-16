package lesson2.tds


fun main() {
    val stk = makeStack<String>()
    stk.push("ISEL")
    stk.push("TDS")
    println(stk.peek()) // TDS
    while( !stk.isEmpty() ) {
        println( stk.pop() ) // TDS ISEL
    }
    stk.peek() // throws NoSuchElementException
}

fun <T> makeStack() = MyStack<T>()
