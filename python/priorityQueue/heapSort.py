from random import *

# ------------------------------------------------------------------------------------heap sort not in place (using priority queue)
def heapSortNotInPlace(A):
    pq = PriorityQueue()
    for a in A:
        pq.insert(a)
    for i in range(len(A) - 1, -1, -1):
        A[i] = pq.extractMax()

class PriorityQueue:
    def __init__(self):
        self.H = [None] * 10000
        self.size = 0
        self.maxSize = 10000

    def __parent(self, i):
        return i // 2
    
    def __leftChild(self, i):
        return i * 2
    
    def __rightChild(self, i):
        return i * 2 + 1
    
    def __swap(self, i, j):
        self.H[i], self.H[j] = self.H[j], self.H[i]
    
    def __shiftUp(self, i):
        while i > 1 and self.H[i - 1] > self.H[self.__parent(i) - 1]:
            p = self.__parent(i)
            self.__swap(i - 1, p - 1)
            i = p

    def __shiftDown(self, i):
        maxIdx = i
        if i <= self.size:
            l = self.__leftChild(i)
            if l <= self.size and self.H[maxIdx - 1] < self.H[l - 1]:
                maxIdx = l

            r = self.__rightChild(i)
            if r <= self.size and self.H[maxIdx - 1] < self.H[r - 1]:
                maxIdx = r

            if i != maxIdx:
                self.__swap(i - 1, maxIdx - 1)
                self.__shiftDown(maxIdx)


    def insert(self, p):
        if self.size == self.maxSize:
            raise IndexError
        self.H[self.size] = p
        self.size += 1
        self.__shiftUp(self.size)

    def extractMax(self):
        if self.size == 0:
            raise IndexError

        result = self.H[0]
        self.H[0] = self.H[self.size - 1]
        self.size -= 1
        self.__shiftDown(1)

        return result

    def remove(self, i):
        if i > self.size or i < 1:
            raise IndexError

        self.H[i - 1] = self.H[0] + 1
        self.__shiftUp(i)
        self.extractMax()

    def changePriority(self, i, p):
        if i > self.size or i < 1:
            raise IndexError

        oldp = self.H[i - 1]
        self.H[i - 1] = p
        if p > oldp:
            self.__shiftUp(i)
        else:
            self.__shiftDown(i)



# ------------------------------------------------------------------------------------heap sort in place
def leftChild(i):
    return i * 2

def rightChild(i):
    return i * 2 + 1

def swap(A, i, j):
    A[i], A[j] = A[j], A[i]

def shiftDown(A, i, size):
    maxIdx = i

    if i <= size:
        l = leftChild(i)
        if l <= size and A[maxIdx - 1] < A[l - 1]:
            maxIdx = l

        r = rightChild(i)
        if r <= size and A[maxIdx - 1] < A[r - 1]:
            maxIdx = r

        if i != maxIdx:
            swap(A, i - 1, maxIdx - 1)
            shiftDown(A, maxIdx, size)

def buildHeap(A, n):
    for i in range(n//2, 0, -1):
        shiftDown(A, i, n)

def heapSortInPlace(A):
    size = len(A)
    buildHeap(A, size)

    while size > 1:
        swap(A, 0, size - 1)
        size -= 1
        shiftDown(A, 1, size)



def main():
    A1 = [22, 40, 19, 3, 7, 12, 16, 19, 32, 8]
    A2 = A1[0:]
    heapSortNotInPlace(A1)
    print(A1)
    
    heapSortInPlace(A2)
    print(A2)

def randomTest():
    l,r = map(int, input().split())

    while True:
        A1 = [None] * l
        A2 = [None] * l
        for i in range(0, l):
            val = randint(0, r)
            A1[i] = val
            A2[i] = val

        print(A1, A2)
        heapSortNotInPlace(A1)
        heapSortInPlace(A2)

        if A1 != A2:
            print(A1, A2)
            break
        else:
            print('pass!')

if __name__ == '__main__':
    # main()
    randomTest()
