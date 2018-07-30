class PriorityQueue:
    def __init__(self):
        self.H = [None] * 15
        self.size = -1
        self.maxSize = 14

    def toString(self):
        return ','.join([str(self.H[i]) for i in range(self.size + 1)])

    def parent(self, i):
        return (i-1)//2

    def leftChild(self, i):
        return i*2+1

    def rightChild(self, i):
        return i*2+2

    def swap(self, i, j):
        self.H[i], self.H[j] = self.H[j], self.H[i]

    def shiftUp(self, i):
        while i > 0 and self.H[i] > self.H[self.parent(i)]:
            p = self.parent(i)
            self.swap(i, p)
            i = p

    def shiftDown(self, i):
        maxIdx = i

        if i <= self.size:
            l = self.leftChild(i)
            if l <= self.size and self.H[maxIdx] < self.H[l]:
                maxIdx = l

            r = self.rightChild(i)
            if r <= self.size and self.H[maxIdx] < self.H[r]:
                maxIdx = r

            if i != maxIdx:
                self.swap(i, maxIdx)
                self.shiftDown(maxIdx)

    def insert(self, p):
        if self.size == self.maxSize:
            raise IndexError
        self.size += 1
        self.H[self.size] = p
        self.shiftUp(self.size)

    def extractMax(self):
        if self.size == -1:
            raise IndexError

        result = self.H[0]
        self.H[0] = self.H[self.size]
        self.size -= 1
        self.shiftDown(0)
        return result

    def remove(self, i):
        if i > self.size or i < 0:
            raise IndexError
        self.H[i] = self.H[0] + 1
        self.shiftUp(i)
        self.extractMax()

    def changePriority(self, i, p):
        if i > self.size or i < 0:
            raise IndexError
        oldp = self.H[i]
        self.H[i] = p
        if p > oldp:
            self.shiftUp(i)
        else:
            self.shiftDown(i)


