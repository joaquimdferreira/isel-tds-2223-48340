package homework.tds

import java.util.NoSuchElementException

class Node<T>(val value: T,val next: Node<T>?)

class FixedStack<T>(private val head: Node<T>? = null) : Stack<T>{
    override fun push(item: T) =
        if(head == null) FixedStack<T>(Node(item, null))
        else FixedStack(Node(item, head))

    override fun peek() = head?.value ?: throw NoSuchElementException("Stack is empty.")

    override fun isEmpty() = head == null

    override fun pop(): FixedStack<T> {
        val curr = head?: throw NoSuchElementException("Stack is empty.")
        return FixedStack(curr.next)
    }
}

fun <T> makeStack() = FixedStack<T>()