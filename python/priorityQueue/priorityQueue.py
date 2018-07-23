class PriorityQueue:
    def __init__(self):
        self.H = [None] * 15
        self.size = 0
        self.maxSize = 15

    def toString(self):
        return ','.join([str(self.H[i]) for i in range(self.size)])

    def parent(self, i):
        return i//2

    def leftChild(self, i):
        return i*2

    def rightChild(self, i):
        return i*2+1

    def swap(self, i, j):
        self.H[i], self.H[j] = self.H[j], self.H[i]

    def shiftUp(self, i):
        while i > 1 and self.H[i - 1] > self.H[self.parent(i) - 1]:
            p = self.parent(i) - 1
            self.swap(i - 1, p)
            i = p + 1

    def shiftDown(self, i):
        maxIdx = i

        if i <= self.size:
            l = self.leftChild(i)
            if l <= self.size and self.H[maxIdx - 1] < self.H[l - 1]:
                maxIdx = l

            r = self.rightChild(i)
            if r <= self.size and self.H[maxIdx - 1] < self.H[r - 1]:
                maxIdx = r

            if i != maxIdx:
                self.swap(i - 1, maxIdx - 1)
                self.shiftDown(maxIdx)

    def insert(self, p):
        if self.size == self.maxSize:
            raise IndexError
        self.size += 1
        self.H[self.size - 1] = p
        self.shiftUp(self.size)

    def extractMax(self):
        result = self.H[0]
        self.H[0] = self.H[self.size - 1]
        self.size -= 1
        self.shiftDown(1)
        return result

    def remove(self, i):
        if i > self.size:
            raise IndexError
        self.H[i - 1] = self.H[0] + 1
        self.shiftUp(i)
        self.extractMax()

    def changePriority(self, i, p):
        if i > self.size or i < 1:
            raise IndexError
        oldp = self.H[i - 1]
        self.H[i - 1] = p
        if p > oldp:
            self.shiftUp(i)
        else:
            self.shiftDown(i)


