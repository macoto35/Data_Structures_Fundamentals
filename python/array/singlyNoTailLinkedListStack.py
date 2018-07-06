class Stack:
    head = None
    size = 0

    class Node:
        next = None
        def __init__(self, data):
            self.data = data

    def toString(self):
        tmp = self.head
        result = []
        for i in range(self.size):
            result.append(str(tmp.data))
            tmp = tmp.next
        return ','.join(result)

    def push(self, input):
        node = self.Node(input)
        node.next = self.head
        self.head = node
        self.size += 1

    def top(self):
        if self.head == None:
            return None
        else:
            return self.head.data
    
    def pop(self):
        if self.head == None:
            return None
        node = self.head
        self.head = node.next
        result = node.data
        node = None
        self.size -= 1
        return result
    
    def empty(self):
        return self.head == None
