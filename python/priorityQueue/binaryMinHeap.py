class BinaryMinHeap:
    def __init__(self):
        self.H = [None] * 15
        self.size = -1
        self.maxSize = 14

    def toString(self):
        return ','.join([ str(self.H[i]) for i in range(self.size + 1) ])

    def __parent(self, i):
        return (i - 1) // 2

    def __leftChild(self, i):
        return i * 2 + 1

    def __rightChild(self, i):
        return i * 2 + 2

    def __swap(self, i, j):
        self.H[i], self.H[j] = self.H[j], self.H[i]

    def __shiftUp(self, i):
        while i > 0 and self.H[i] < self.H[self.__parent(i)]:
            p = self.__parent(i)
            self.__swap(i, p)
            i = p

    def __shiftDown(self, i):
        minIdx = i

        if i <= self.size:
            l = self.__leftChild(i)
            if l <= self.size and self.H[minIdx] > self.H[l]:
                minIdx = l

            r = self.__rightChild(i)
            if r <= self.size and self.H[minIdx] > self.H[r]:
                minIdx = r

            if i != minIdx:
                self.__swap(i, minIdx)
                self.__shiftDown(minIdx)

    def insert(self, p):
        if self.size == self.maxSize:
            raise IndexError
        self.size += 1
        self.H[self.size] = p
        self.__shiftUp(self.size)

    def extractMin(self):
        if self.size == -1:
            raise IndexError
        result = self.H[0]
        self.H[0] = self.H[self.size]
        self.size -= 1
        self.__shiftDown(0)

        return result

    def remove(self, i):
        if i > self.size or i < 0:
            raise IndexError
        self.H[i] = self.H[0] - 1
        self.__shiftUp(i)
        self.extractMin()

    def changePriority(self, i, p):
        if i > self.size or i < 0:
            raise IndexError
        oldp = self.H[i]
        self.H[i] = p
        if oldp > p:
            self.__shiftUp(i)
        else:
            self.__shiftDown(i)




