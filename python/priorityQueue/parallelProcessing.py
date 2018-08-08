
class MinHeap:
    def __init__(self, maxSize):
        self.H = [None] * maxSize
        self.size = -1
        self.maxSize = maxSize

    def _parent(self, i):
        return (i - 1) // 2

    def _leftChild(self, i):
        return 2 * i + 1

    def _rightChild(self, i):
        return 2 * i + 2

    def _swap(self, i, j):
        self.H[i], self.H[j] = self.H[j], self.H[i]

    def _shiftUp(self, i):
        while i > 0:
            c = self.H[i]
            p = self.H[self._parent(i)]
            if c.time < p.time or (c.time == p.time and c.thread < p.thread):
                self._swap(i, self._parent(i))
            i = self._parent(i)

    def _shiftDown(self, i):
        if i <= self.size:
            minIdx = i
            min = self.H[minIdx]
            
            lidx = self._leftChild(i)
            if lidx <= self.size:
                l = self.H[lidx]
                if min.time > l.time or (min.time == l.time and min.thread > l.thread):
                    minIdx = lidx
                    min = l

            ridx = self._rightChild(i)
            if ridx <= self.size:
                r = self.H[ridx]
                if min.time > r.time or (min.time == r.time and min.thread > r.thread):
                    minIdx = ridx
                    min = r

            if minIdx != i:
                self._swap(i, minIdx)
                self._shiftDown(minIdx)

    class Data:
        def __init__(self, time, thread):
            self.time = time
            self.thread = thread

        def __str__(self):
            return str(self.time) + '|' + str(self.thread)

        def __repr__(self):
            return self.__str__()

    def insert(self, time, thread):
        self.size += 1
        self.H[self.size] = self.Data(time, thread)
        self._shiftUp(self.size)

    def extractMin(self):
        result = self.H[0]
        self.H[0] = self.H[self.size]
        self.size -= 1
        self._shiftDown(0)

        return result


def parallel(n, m, t):
    heap = MinHeap(n)

    for i in range(n):
        heap.insert(0, i)

    for i in range(m):
        result = heap.extractMin()
        heap.insert(t[i] + result.time, result.thread)
        print(result.thread, result.time)
        #print(result.thread, result.time, end=' ')

n, m = map(int, input().split())
t = [*map(int, input().split())]
parallel(n, m, t)
