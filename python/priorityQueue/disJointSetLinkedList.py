class DisJointSetLinkedList:
    def __init__(self):
        self.head = [None] * 9
        self.tail = [None] * 9
        self.size = -1
        self.maxSize = 9

    class Node:
        def __init__(self, value):
            self.value =  value
            self.next = None

    def makeSet(self, p):
        if self.size == self.maxSize - 1:
            raise IndexError
        
        node = self.Node(p)
        self.size += 1
        self.head[self.size] = node
        self.tail[self.size] = node

    def find(self, i):
        if i < 0 or i > self.size:
            raise IndexError
        return self.tail[i].value

    def union(self, i, j):
        self.tail[i].next = self.head[j]
        self.head[j] = self.head[i]
        
        item = self.head[j]
        while item is not None:
            self.tail[item.value] = self.tail[j]
            item = item.next
