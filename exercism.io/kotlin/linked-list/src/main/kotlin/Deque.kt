class Deque<T> {
    private class Node<T> {
        var value: T? = null
        var pre: Node<T>? = null
        var next: Node<T>? = null
    }

    private val head = Node<T>()

    fun push(data: T) {
        val node = Node<T>()
        node.value = data
        val findLast = findLast(head)
        findLast?.next = node
        node.pre = findLast
    }

    fun pop() : T? {
        val last = findLast(head)
        last?.pre?.next = null
        return last?.value
    }

    fun unshift(data: T) {
        val node = Node<T>()
        node.value = data
        node.next = head.next
        node.next?.pre = node
        head.next = node
    }

    fun shift() : T? {
        val node = head.next
        head.next = node?.next
        head.next?.pre = head
        return node?.value
    }

    private fun findLast(root: Node<T>?) : Node<T>? {
        var last: Node<T>? = root
        while (last?.next != null) last = last?.next
        return last
    }
}