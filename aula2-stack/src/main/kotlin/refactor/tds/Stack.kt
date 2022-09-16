package refactor.tds

import java.util.NoSuchElementException

class Node<T>(val value: T,val next: Node<T>? = null)

class Stack<T>() {
    private var head: Node<T>? = null
    fun push(value: T) {
        head = if(head == null) Node(value, null)
        else Node(value, head)
    }

    fun pop(): T?{
        if(head == null)return null
        val curr = head
        head = head?.next
        return curr!!.value
    }

    fun isEmpty() = head == null

    fun peek() = head?.value ?: throw NoSuchElementException()

}