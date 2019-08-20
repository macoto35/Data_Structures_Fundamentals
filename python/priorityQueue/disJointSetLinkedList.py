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
    '''
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
            item = item.next'''

    def makeSet(self, p):
        if self.size == self.maxSize - 1:
            raise IndexError

        node = self.Node(p)
        self.size += 1
        self.tail[self.size] = node

    def find(self, i):
        if i < 0 or i > self.size:
            raise IndexError

        current = self.tail[i]
        while current.next != None:
            current = current.next

        return current.value

    def union(self, i, j):
        i_id = self.find(i)
        j_id = self.find(j)

        if i_id == j_id:
            return

        self.tail[i_id].next = self.tail[j]
