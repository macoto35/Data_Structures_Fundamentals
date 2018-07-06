class SinglyLinkedList:
    head = None
    tail = None
    length = 0

    def __init__(self):
        pass

    class Node:
        def __init__(self, data):
            self.data = data
            self.next = None

    def toString(self):
        tmp = self.head
        result = []
        for i in range(self.length):
            result.append(tmp.data)
            tmp = tmp.next
        return ','.join(result)

    def addFirst(self, input):
        node = self.Node(input)
        
        if self.head is not None:
            node.next = self.head
        
        if self.tail is None:
            self.tail = node

        self.head = node
        self.length += 1

    def addLast(self, input):
        node = self.Node(input)

        if self.tail is not None:
            self.tail.next = node

        if self.head is None:
            self.head = node

        self.tail = node
        self.length += 1

    def __node(self, idx):
        tmp = self.head
        for i in range(idx):
            if tmp is not None:
                tmp = tmp.next
        return tmp

    def addFront(self, idx, input):
        if idx == 0:
            self.addFirst(input)
        elif idx >= self.length:
            self.addLast(input)
        else:
            frontNode = self.__node(idx - 1)
            node = self.Node(input)

            node.next = frontNode.next
            frontNode.next = node
            self.length += 1

    def addBack(self, idx, input):
        if idx >= self.length:
            self.addLast(input)
        else:
            frontNode = self.__node(idx)
            node = self.Node(input)

            if frontNode is not None:
                node.next = frontNode.next
                frontNode.next = node

            if self.head is None:
                self.head = self.tail = node

            if node.next is None:
                self.tail = node
            self.length += 1

    def removeFirst(self):
        if self.head is None:
            return None

        deleteNode = self.head
        self.head = deleteNode.next
        result = deleteNode.data
        deleteNode = None
        self.length -= 1

        return result

    def remove(self, idx):
        if idx == 0:
            return self.removeFirst()
        elif idx >= self.length:
            return None
        else:
            prevNode = self.__node(idx - 1)
            deleteNode = prevNode.next

            prevNode.next = deleteNode.next
            if prevNode.next is None:
                self.tail = prevNode
            result = deleteNode.data
            deleteNode = None
            self.length -= 1

            return result

    def removeLast(self):
        if self.length == 0:
            return None
        return self.remove(self.length - 1)

    def size(self):
        return self.length

    def empty(self):
        return self.head is None
    
    def get(self, idx):
        node = self.__node(idx)
        
        if node is None:
            return None
        else:
            return node.data
    
    def indexOf(self, data):
        tmp = self.head

        for i in range(self.length):
            if tmp.data == data:
                return i
            tmp = tmp.next

        return -1
