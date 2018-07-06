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