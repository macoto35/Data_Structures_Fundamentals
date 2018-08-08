
from random import *
from heapq import heapify

def parent(i):
    return (i - 1) // 2

def leftChild(i):
    return 2 * i + 1

def rightChild(i):
    return 2 * i + 2

def swap(A, R, i, j):
    A[i], A[j] = A[j], A[i]
    R.append(str(i) + ' ' + str(j))

def shiftUp(A, R, i):
    while i > 0:
        if A[i] < A[parent(i)]:
            swap(A, R, parent(i), i)
        i = parent(i)

def buildHeap(n, A):
    R = []
    for i in range(n - 1, 0, -1):
        shiftUp(A, R, i)
    return R

# input
'''
n = int(input())
A = [*map(int, input().split())]
buildHeap(n, A)
'''

# random value test
N, I= map(int, input().split())

while True:
    n = randint(1, N)
    A = [-1] * n
    for i in range(n):
        A[i] = randint(0, I)

    print('------------------------------------------------------------')
    R = buildHeap(n, A)
    print(len(R))
    for item in R:
        print(item)
    
    if len(R) > 4*n:
        print('error!', len(R), 4*n)
        break

