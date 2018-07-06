class SinglyTailLinkedQueue:
    def __init__(self):
        self.head = None
        self.tail = None
        self.size = 0

    class Node:
        def __init__(self, data):
            self.data = data
            self.next = None

    def toString(self):
        tmp = self.head
        result = []
        for i in range(self.size):
            result.append(str(tmp.data))
            tmp = tmp.next
        return ','.join(result)


    def enqueue(self, input):
        node = self.Node(input)
        if self.tail == None:
            self.head = node
        else:
            self.tail.next = node
        self.tail = node
        self.size += 1

    def dequeue(self):
        if self.head == None:
            return None
        node = self.head
        result = node.data
        self.head = node.next
        node = None
        self.size -= 1
        return result

    def empty(self):
        return self.head == None
