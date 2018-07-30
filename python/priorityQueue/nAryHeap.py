class NAryHeap:
    def __init__(self, n):
        self.n = n
        self.H = [None] * 1
        self.size = -1
        self.maxSize = 1

    def toString(self):
        return ','.join([str(self.H[i]) for i in range(self.size + 1)])

    def _parent(self, i):
        return (i - 1) // self.n
    
    def _leftChild(self, i):
        return i * self.n + 1

    def _rightChild(self, i):
        return i * self.n + self.n

    def _swap(self, i, j):
        self.H[i], self.H[j] = self.H[j], self.H[i]

    def _shiftUp(self, i):
        while i > 0 and self.H[i] > self.H[self._parent(i)]:
            p = self._parent(i)
            self._swap(i, p)
            i = p

    def _shiftDown(self, i):
        maxIdx = i

        for cIdx in range(self._leftChild(i), self._rightChild(i) + 1):
            if cIdx <= self.size and self.H[maxIdx] < self.H[cIdx]:
                maxIdx = cIdx

        if i != maxIdx:
            self._swap(i, maxIdx)
            self._shiftDown(maxIdx)

    def insert(self, p):
        if self.size == self.maxSize - 1:
            newH = [None] * (self.maxSize * self.n)
            for i in range(self.maxSize):
                newH[i] = self.H[i]
            self.H = newH
            self.maxSize = self.maxSize * self.n

        self.size += 1
        self.H[self.size] = p
        self._shiftUp(self.size)

    def extractMax(self):
        if self.size == -1:
            raise IndexError
        result = self.H[0]
        self.H[0] = self.H[self.size]
        self.size -= 1
        self._shiftDown(0)

        return result

    def remove(self, i):
        if i < 0 or i > self.size:
            raise IndexError
        self.H[i] = self.H[0] + 1
        self._shiftUp(i)
        self.extractMax()

    def changePriority(self, p, i):
        if i < 0 or i > self.size:
            raise IndexError
        oldp = self.H[i]
        self.H[i] = p
        if oldp < p:
            self._shiftUp(i)
        else:
            self._shiftDown(i)
