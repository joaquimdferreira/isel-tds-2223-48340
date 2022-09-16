package refactor.tds

import java.util.NoSuchElementException

class Node<T>(val value: T,val next: Node<T>?)

class MyStack<T> : Stack<T>{
    private var head: Node<T>? = null
    override fun push(value: T) {
        head =  Node(value, head)
    }

    override fun pop(): T{
        val curr = head ?: throw NoSuchElementException()
        head = curr.next
        return curr.value
    }

    override fun isEmpty() = head == null

    override fun peek() = head?.value ?: throw NoSuchElementException("The stack is empty")

}