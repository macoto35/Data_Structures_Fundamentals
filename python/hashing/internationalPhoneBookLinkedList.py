class InternationalPhoneBookLinkedList:
    def __init__(self):
        self.head = None
        self.tail = None

    class Node:
        def __init__(self, number, name):
            self.number = number
            self.name = name
            self.next = None
            self.prev = None

    def setName(self, number, name):
        node = self.Node(number, name)

        if self.head is None:
            self.head = node
        
        if self.tail is not None:
            self.tail.next = node
            node.prev = self.tail

        self.tail = node

    def getName(self, number):
        node = self.head

        while node is not None:
            if node.number == number:
                return node.name
            node = node.next

        return None
