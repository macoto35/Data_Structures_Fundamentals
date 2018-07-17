'''import sys
import threading

def computeHeight(n, parents):
    maxHeight = 0
    for vertex in range(n):
        height = 0
        current = vertex
        while current != -1:
            height += 1
            current = parents[current]
        maxHeight = max(maxHeight, height)
    return maxHeight

def main():
    n = int(input())
    parents = list(map(int, input().split()))
    print(computeHeight(n, parents))

sys.setrecursionlimit(10**7)
threading.stack_size(2**27)
threading.Thread(target=main).start()'''

'''
def solutionOneGreedy(arr):
    result = []

    while(len(arr) > 0):
        item = arr[0];
        if item == -1:
            arr.pop(0)
        else:
            i = 0
            while i < len(arr):
                if item == arr[i]:
                    arr.pop(i)
                else:
                    i += 1
        result.append(item)

    return len(result)

def solutionTwoBrutalForce(n, parents):
    result = [-1 for i in range(n)]

    size = 0
    for node in parents:
        if result[node] == -1:
            result[node] = node
            size += 1

    return size

n = int(input())
parents = list(map(int, input().split()))
print(solutionOneGreedy(parents[:]))
print(solutionTwoBrutalForce(n, parents))
'''

import sys

class Node:
    def __init__(self):
        self.children = []
class Queue:
    head = None
    tail = None

    class QueueNode:
        def __init__(self, data):
            self.data = data
            self.next = None

    def enqueue(self, input):
        node = self.QueueNode(input)

        if self.head is None:
            self.head = node
        if self.tail is not None:
            self.tail.next = node
        self.tail = node
    
    def dequeue(self):
        if self.head is None:
            return None
        node = self.head
        self.head = self.head.next
        result = node.data
        node = None

        if self.head is None:
            self.tail = None

        return result

    def topBack(self):
        if self.tail is None:
            return None
        return self.tail.data

    def empty(self):
        return self.head is None

def setTree(parents, size):
    nodes = [Node() for i in range(size)]
    root_idx = -1

    for i in range(size):
        parent = parents[i]
        if parent == -1:
            root_idx = i
        else:
            nodes[parent].children.append(i)

    result = []
    # solution 1: using queue to count length
    result.append(getLength1(root_idx, nodes))

    # solution 2: using recursive
    result.append(getLength2(root_idx, nodes))

    return result

def getLength1(root_idx, nodes):
    last = root_idx
    length = 0
    queue = Queue()
    queue.enqueue(root_idx)

    while queue.empty() == False:
        idx = queue.dequeue()
        
        for child in nodes[idx].children:
            queue.enqueue(child)

        if last == idx:
            length += 1
            last = queue.topBack()
    return length

def getLength2(idx, nodes):
    c = nodes[idx].children
    
    if len(c) == 0:
        return 1

    result = []
    for i in c:
        cnt = getLength2(i, nodes) + 1
        result.append(cnt)

    return max(result)


def main():
    #length = int(input())
    #parents = [*map(int, input().split())]
    #print(setTree(parents, length))

    sys.setrecursionlimit(10**5)
    file = open('../../sample/tree_height_test.in', 'r')
    while True:
        line1 = file.readline()
        line2 = file.readline()
        line3 = file.readline()

        if not line1:
            break
        else:
            arr = setTree([*map(int,line2.split())], int(line1))
            if arr[0] != arr[1]:
                print(arr)
            else:
                print('success!')

if __name__ == '__main__':
    main()

