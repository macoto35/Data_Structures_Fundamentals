class DoublyLinkedList:
    head = None
    tail = None
    length = 0

    class Node:
        next = None
        prev = None
        def __init__(self, data):
            self.data = data
    
    def toString(self):
        tmp = self.head
        result = []
        for i in range(self.length):
            result.append(tmp.data)
            tmp = tmp.next
        return ','.join(result)

    def addFirst(self, input):
        node = self.Node(input)
        node.next = self.head

        if self.head is not None:
            self.head.prev = node

        if self.tail is None:
            self.tail = node

        self.head = node
        self.length += 1

    def addLast(self, input):
        node = self.Node(input)
        node.prev = self.tail

        if self.tail is not None:
            self.tail.next = node

        if self.head is None:
            self.head = node

        self.tail = node
        self.length += 1

    def __node(self, idx):
        if idx <= self.length // 2:
           tmp = self.head
           for i in range(idx):
               tmp = tmp.next
        else:
            tmp = self.tail
            for i in range(self.length - 1, idx, -1):
                tmp = tmp.prev

        return tmp

    def addFront(self, idx, input):
        if idx == 0:
            self.addFirst(input)
        elif idx >= self.length:
            self.addLast(input)
        else:
            node = self.Node(input)
            nextNode = self.__node(idx)

            node.prev = nextNode.prev
            nextNode.prev.next = node
            node.next = nextNode
            nextNode.prev = node

            self.length += 1

    def addBack(self, idx, input):
        if idx >= self.length:
            self.addLast(input)
        else:
            node = self.Node(input)
            prevNode = self.__node(idx)
            
            if prevNode is None:
                self.head = node
            else:
                node.next = prevNode.next
                node.prev = prevNode
                prevNode.next = node

            if node.next is None:
                self.tail = node
            else:
                node.next.prev = node

            self.length += 1

    def removeFront(self):
        if self.head is None:
            return None

        deleteNode = self.head
        self.head = deleteNode.next
        result = deleteNode.data
        deleteNode = None
        self.length -= 1

        return result

    def removeBack(self):
        if self.tail is None:
            return None

        deleteNode = self.tail
        self.tail = deleteNode.prev
        result = deleteNode.data
        deleteNode = None
        self.length -= 1

        return result

    def remove(self, idx):
        if idx >= self.length:
            return None
        
        deleteNode = self.__node(idx)
        prevNode = deleteNode.prev
        nextNode = deleteNode.next

        if prevNode is None:
            self.head = nextNode
        else:
            prevNode.next = nextNode

        if nextNode is None:
            self.tail = prevNode
        else:
            nextNode.prev = prevNode

        result = deleteNode.data
        deleteNode = None
        self.length -= 1

        return result

    def size(self):
        return self.length

    def empty(self):
        return self.head is None

    def get(self, idx):
        if idx >= self.length:
            return None
        return self.__node(idx).data

    def indexOf(self, data):
        tmp = self.head
        for i in range(self.length):
            if data == tmp.data:
                return i
            tmp = tmp.next
        return -1



